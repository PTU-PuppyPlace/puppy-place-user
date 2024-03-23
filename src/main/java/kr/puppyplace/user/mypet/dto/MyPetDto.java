package kr.puppyplace.user.mypet.dto;

import kr.puppyplace.user.mypet.domain.Pet;
import kr.puppyplace.user.mypet.domain.enums.PetGender;
import kr.puppyplace.user.mypet.domain.enums.PetNeutralization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class MyPetDto {

//    private Long petId;
//
//    private String petName;
//
//    public static MyPetDto of(MyPetDto pet) {
//        return MyPetDto.builder()
//                .petId(pet.getPetId())
//                .petName(pet.getPetName())
//                .build();
//    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyPetCreateRequest {
        private Long petId;
        private String petName;
        private String petRegNumber; // 등록 번호
        private String petBirth; // 출생년도
        private String petBreed; // 품종
        private PetGender petGender; // 성별
        private PetNeutralization petNeutralization; // 중성화 여부
        private int petWeight; // 몸무게
        private String petTemperament; // 성격
    }

    @Builder
    @Getter
    public static class MyPetCreateResponse {
        private Long petId;
        private String petName;
        private String petRegNumber; // 등록 번호
        private String petBirth; // 출생년도
        private String petBreed; // 품종
        private PetGender petGender; // 성별
        private PetNeutralization petNeutralization; // 중성화 여부
        private int petWeight; // 몸무게
        private String petTemperament; // 성격

        public static MyPetCreateResponse from(Pet pet) {
            return MyPetCreateResponse.builder()
                    .petId(pet.getPetId())
                    .petName(pet.getPetName())
                    .petRegNumber(pet.getPetRegNumber())
                    .petBirth(pet.getPetBirth())
                    .petBreed(pet.getPetBreed())
                    .petGender(pet.getPetGender())
                    .petNeutralization(pet.getPetNeutralization())
                    .petWeight(pet.getPetWeight())
                    .petTemperament(pet.getPetTemperament())
                    .build();
        }
    }
}
