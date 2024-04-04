package kr.puppyplace.user.mypet.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import kr.puppyplace.user.mypet.domain.enums.PetGender;
import kr.puppyplace.user.mypet.domain.enums.PetNeutralization;
import kr.puppyplace.user.mypet.dto.BreedDto;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateResponse;
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
    BreedServiceImpl breedService;

    @Test
    @DisplayName("반려동물 정보를 등록할 수 있어야 한다.")
    public void 반려동물_정보를_등록할_수_있어야_한다() {
        //given
        MyPetCreateRequest testPet = MyPetCreateRequest.builder()
                .petName("테스트")
                .petBreed("test")
                .petGender(PetGender.FEMALE)
                .petBirth(LocalDate.of(2024, 4, 1))
                .petWeight(10)
                .petTemperament("활발")
                .petNeutralization(PetNeutralization.valueOf("YES"))
                .petRegNumber("1234567890")
                .build();

        //when
        MyPetCreateResponse responsePet = myPetService.myPetCreate(testPet);

        //then
        assertThat(responsePet.getId()).isNotNull();
        assertThat(responsePet.getPetName()).isEqualTo(testPet.getPetName());
        assertThat(responsePet.getPetBreed()).isEqualTo(testPet.getPetBreed());

    }

    @Test
    @DisplayName("견종 검색을 할 수 있어야 한다.")
    void 견종_검색을_할_수_있어야_한다() {
        //given
        String breedKey = "콜리";

        //when
        List<BreedDto> byBreedNameContaining = breedService.findByBreedNameContaining(breedKey);

        //then
        assertThat(byBreedNameContaining.stream().allMatch
                (b -> b.getBreedName().contains(breedKey) || b.getBreedEngName().contains(breedKey)
                )).isTrue();

    }

    @Test
    @DisplayName("견종 검색 시 검색어가 없는 경우 빈 리스트를 반환해야한다.")
    void 견종검색_없는_키워드_입력() {
        //given
        String breedKey = "없는키";

        //when
        List<BreedDto> byBreedNameContaining = breedService.findByBreedNameContaining(breedKey);

        //then
        assertThat(byBreedNameContaining.size()).isEqualTo(0);
    }

}