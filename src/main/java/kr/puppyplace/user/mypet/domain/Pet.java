package kr.puppyplace.user.mypet.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import kr.puppyplace.user.mypet.domain.enums.PetGender;
import kr.puppyplace.user.mypet.domain.enums.PetNeutralization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pet_id")
    private Long petId;

    @NotNull
    @Comment("반려동물 이름")
    @Column(name = "pet_name")
    private String petName;

    @Comment("반려동물 등록번호")
    @Column(name = "pet_regnumber")
    private String petRegNumber;

    @NotNull
    @Comment("반려동물 출생년도")
    @Column(name = "pet_birth")
    private String petBirth;

    @NotNull
    @Comment("반려동물 품종")
    @Column(name = "pet_breed")
    private String petBreed;

    @NotNull
    @Comment("반려동물 성별")
    @Column(name = "pet_gender", columnDefinition = "ENUM('MALE' , 'FEMALE')")
    @Enumerated(EnumType.STRING)
    private PetGender petGender;

    @NotNull
    @Comment("반려동물 중성화 여부")
    @Column(name = "pet_neutralization", columnDefinition = "ENUM('YES', 'NO')")
    @Enumerated(EnumType.STRING)
    private PetNeutralization petNeutralization;

    @NotNull
    @Comment("반려동물 몸무게")
    @Column(name = "pet_weight")
    private int petWeight;

    @Comment("반려동물 성격")
    @Column(name = "pet_temperament")
    private String petTemperament;

    public static Pet createPet(String petName, String petRegNumber, String petBirth,
        String petBreed, PetGender petGender, PetNeutralization petNeutralization, int petWeight,
        String petTemperament) {
        return Pet.builder()
            .petName(petName)
            .petRegNumber(petRegNumber)
            .petBirth(petBirth)
            .petBreed(petBreed)
            .petGender(petGender)
            .petNeutralization(petNeutralization)
            .petWeight(petWeight)
            .petTemperament(petTemperament)
            .build();
    }
}
