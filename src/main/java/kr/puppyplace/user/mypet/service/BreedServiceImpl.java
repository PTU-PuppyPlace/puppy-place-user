package kr.puppyplace.user.mypet.service;

import java.util.List;
import kr.puppyplace.user.mypet.domain.Breed;
import kr.puppyplace.user.mypet.dto.BreedDto;
import kr.puppyplace.user.mypet.repository.BreedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BreedDto> findByBreedNameContaining(String breedKey) {
        List<Breed> findBreeds = breedRepository.findByBreedNameContainingOrBreedEngNameContaining(
                breedKey, breedKey);
        log.info("findBreeds : " + findBreeds);

        return findBreeds.stream().map(b -> BreedDto.builder()
                        .id(b.getId())
                        .breedName(b.getBreedName())
                        .breedEngName(b.getBreedEngName())
                        .build())
                .toList();

    }

}
