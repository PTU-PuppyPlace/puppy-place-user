package kr.puppyplace.user.mypet.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Breed  {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    @Comment("breed_id")
    private Long Id;

    @Column(name = "breed_name")
    private String breedName;

    @Column(name = "breed_eng_name")
    private String breedEngName;

    public static Breed createBreed(String breedName, String breedEngName) {
        return Breed.builder()
                .breedName(breedName)
                .breedEngName(breedEngName)
                .build();
    }
}


