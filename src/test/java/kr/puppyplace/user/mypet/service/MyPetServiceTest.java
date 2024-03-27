package kr.puppyplace.user.mypet.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import java.util.List;
import kr.puppyplace.user.mypet.domain.Pet;
import kr.puppyplace.user.mypet.domain.enums.PetGender;
import kr.puppyplace.user.mypet.domain.enums.PetNeutralization;
import kr.puppyplace.user.mypet.dto.BreedDto;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.repository.MyPetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class MyPetServiceTest {

    @Autowired
    MyPetService myPetService;
    @Autowired
    MyPetRepository myPetRepository;
    @Autowired
    BreedServiceImpl BreedService;

    @Test
    @DisplayName("반려동물 정보를 입력하여 등록할 수 있어야한다.")
    public void 반려동물_등록() {
        //given
        MyPetCreateRequest testPet = MyPetCreateRequest.builder()
                .petName("테스트")
                .petBreed("test")
                .petGender(PetGender.valueOf("FEMALE"))
                .petBirth("20210101")
                .petWeight(10)
                .petTemperament("활발")
                .petNeutralization(PetNeutralization.valueOf("YES"))
                .petRegNumber("1234567890")
                .build();

        //when
        Pet savePet = myPetRepository.save(
                Pet.createPet(testPet.getPetName(), testPet.getPetRegNumber(),
                        testPet.getPetBirth(), testPet.getPetBreed(), testPet.getPetGender(),
                        testPet.getPetNeutralization(), testPet.getPetWeight(),
                        testPet.getPetTemperament()));
        Long servicePetId = myPetService.myPetCreate(testPet).getPetId();

        //then
        assertThat(savePet.getPetId(), samePropertyValuesAs(servicePetId));

    }

    @Test
    @DisplayName("견종 검색을 할 수 있어야한다.")
    void 견종검색() {
        //given
        String breedKey = "콜리";

        //when
        List<BreedDto> byBreedNameContaining = BreedService.findByBreedNameContaining(breedKey);

        //then
        assertThat(byBreedNameContaining.stream().allMatch
                (b -> b.getBreedName().contains(breedKey) || b.getBreedEngName().contains(breedKey)
                ), samePropertyValuesAs(true));

    }

    @Test
    @DisplayName("견종 검색 시 검색어가 없는 경우 빈 리스트를 반환해야한다.")
    void 견종검색_없는_키워드_입력() {
        //given
        String breedKey = "없는키";

        //when
        List<BreedDto> byBreedNameContaining = BreedService.findByBreedNameContaining(breedKey);

        //then
        assertThat(byBreedNameContaining.size(), samePropertyValuesAs(0));
    }

}