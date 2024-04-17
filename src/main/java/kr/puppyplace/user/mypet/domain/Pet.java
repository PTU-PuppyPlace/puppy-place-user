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
import kr.puppyplace.user.mypet.enums.PetGender;
import kr.puppyplace.user.mypet.enums.PetNeutralization;
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
    @Column(name = "NAME")
    private String name;

    @Comment("반려동물 등록번호")
    @Column(name = "REGISTER_NUMBER")
    private String registerNumber;

    @NotNull
    @Comment("반려동물 출생년도")
    @Column(name = "BIRTH")
    private LocalDate birth;

    @NotNull
    @Comment("반려동물 품종")
    @Column(name = "BREED")
    private String breed;

    @NotNull
    @Comment("반려동물 성별")
    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private PetGender gender;

    @NotNull
    @Comment("반려동물 중성화 여부")
    @Column(name = "NEUTRALIZATION")
    @Enumerated(EnumType.STRING)
    private PetNeutralization neutralization;

    @NotNull
    @Comment("반려동물 몸무게")
    @Column(name = "WEIGHT")
    private Integer weight;

    @Comment("반려동물 성격")
    @Column(name = "TEMPERAMENT")
    private String temperament;

}

