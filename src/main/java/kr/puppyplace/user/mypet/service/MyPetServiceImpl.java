package kr.puppyplace.user.mypet.service;

import kr.puppyplace.user.mypet.domain.Pet;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateResponse;
import kr.puppyplace.user.mypet.repository.MyPetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyPetServiceImpl implements MyPetService {

    private final MyPetRepository myPetRepository;

    @Override
    @Transactional
    public MyPetCreateResponse myPetCreate(MyPetCreateRequest request) {
        Pet pet = myPetRepository.save(
            Pet.builder().petName(request.getPetName()).petRegNumber(request.getPetRegNumber())
                .petBirth(request.getPetBirth()).petBreed(request.getPetBreed())
                .petGender(request.getPetGender()).petNeutralization(request.getPetNeutralization())
                .petWeight(request.getPetWeight()).petTemperament(request.getPetTemperament())
                .build());
        return MyPetCreateResponse.from(pet);
    }

}
