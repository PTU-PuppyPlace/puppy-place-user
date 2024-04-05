package kr.puppyplace.user.mypet.dto;

import java.time.LocalDate;
import kr.puppyplace.user.mypet.domain.Pet;
import kr.puppyplace.user.mypet.enums.PetGender;
import kr.puppyplace.user.mypet.enums.PetNeutralization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MyPetDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyPetCreateRequest {

        private Long id;
        private String name;
        private String regNumber; // 등록 번호
        private LocalDate birth; // 출생년도
        private String breed; // 품종
        private PetGender gender; // 성별
        private PetNeutralization neutralization; // 중성화 여부
        private Integer weight; // 몸무게
        private String temperament; // 성격

        public Pet toEntity() {
            return Pet.builder()
                    .name(this.name)
                    .registerNumber(this.regNumber)
                    .birth(this.birth)
                    .breed(this.breed)
                    .gender(this.gender)
                    .neutralization(this.neutralization)
                    .weight(this.weight)
                    .temperament(this.temperament)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class MyPetCreateResponse {

        private Long id;
        private String name;
        private String regNumber; // 등록 번호
        private LocalDate birth; // 출생년도
        private String breed; // 품종
        private PetGender gender; // 성별
        private PetNeutralization neutralization; // 중성화 여부
        private Integer weight; // 몸무게
        private String temperament; // 성격

        public static MyPetCreateResponse fromEntity(Pet pet) {
            return MyPetCreateResponse.builder()
                    .id(pet.getId())
                    .name(pet.getName())
                    .regNumber(pet.getRegisterNumber())
                    .birth(pet.getBirth())
                    .breed(pet.getBreed())
                    .gender(pet.getGender())
                    .neutralization(pet.getNeutralization())
                    .weight(pet.getWeight())
                    .temperament(pet.getTemperament())
                    .build();
        }
    }
}
