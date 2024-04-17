package kr.puppyplace.user.mypet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import kr.puppyplace.user.mypet.domain.Pet;
import kr.puppyplace.user.mypet.enums.PetGender;
import kr.puppyplace.user.mypet.enums.PetNeutralization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

public class MyPetDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyPetCreateRequest {

        @NotBlank(message = "반려동물의 이름을 입력해 주세요.")
        @Size(max = 100, message = "반려동물의 이름은 100자 이하로 입력해 주세요.")
        private String name;

        @Pattern(regexp = "^\\d*$", message = "반려동물의 등록 번호는 숫자만 입력 가능합니다.")
        @Size(max = 100, message = "반료동물의 등록 번호는 이하로 입력해 주세요.")
        private String registerNumber; // 등록 번호

        @NotBlank(message = "반려동물의 생일을 입력해 주세요.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth; // 출생년도

        @NotBlank(message = "반려동물의 품종을 선택해 주세요.")
        private String breed; // 품종

        @NotBlank(message = "반려동물의 성별을 선택해 주세요.")
        private PetGender gender; // 성별

        @NotBlank(message = "반려동물의 중성화 여부를 선택해 주세요.")
        private PetNeutralization neutralization; // 중성화 여부

        @NotBlank(message = "반려동물의 몸무게를 입력해 주세요.")
        private Integer weight; // 몸무게

        @Size(max = 255, message = "반려동물의 성격은 255자 이하로 입력해 주세요.")
        private String temperament; // 성격

        public Pet toEntity() {
            return Pet.builder()
                    .name(this.name)
                    .registerNumber(this.registerNumber)
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
        private String registerNumber; // 등록 번호
        @JsonFormat(pattern = "yyyy-MM-dd")
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
                    .registerNumber(pet.getRegisterNumber())
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
