package kr.puppyplace.user.mypet.service;

import java.util.List;
import kr.puppyplace.user.mypet.dto.BreedDto;

public interface BreedService {

    List<BreedDto> findByBreedNameContaining(String breedKey);
}
