package kr.puppyplace.user.mypet.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
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
@Table(name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Comment("반려동물 이름")
    @Column(name = "PET_NAME")
    private String petName;

    @Comment("반려동물 등록번호")
    @Column(name = "PET_REGISTER_NUMBER")
    private String petRegisterNumber;

    @NotNull
    @Comment("반려동물 출생년도")
    @Column(name = "PET_BIRTH")
    private LocalDate petBirth;

    @NotNull
    @Comment("반려동물 품종")
    @Column(name = "PET_BREED")
    private String petBreed;

    @NotNull
    @Comment("반려동물 성별")
    @Column(name = "PET_GENDER")
    @Enumerated(EnumType.STRING)
    private PetGender petGender;

    @NotNull
    @Comment("반려동물 중성화 여부")
    @Column(name = "PET_NEUTRALIZATION", columnDefinition = "ENUM('YES', 'NO')")
    @Enumerated(EnumType.STRING)
    private PetNeutralization petNeutralization;

    @NotNull
    @Comment("반려동물 몸무게")
    @Column(name = "PET_WEIGHT")
    private Integer petWeight;

    @Comment("반려동물 성격")
    @Column(name = "PET_TEMPERAMENT")
    private String petTemperament;

}

