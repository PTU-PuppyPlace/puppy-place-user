package kr.puppyplace.user.mypet.service;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import kr.puppyplace.user.mypet.domain.Pet;
import kr.puppyplace.user.mypet.domain.enums.PetGender;
import kr.puppyplace.user.mypet.domain.enums.PetNeutralization;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.repository.MyPetRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Transactional
@SpringBootTest
class MyPetServiceTest {

    @Autowired
    MyPetService myPetService;
    @Autowired
    MyPetRepository myPetRepository;

    @Test
    public void 반려동물_등록() throws Exception {
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
}
