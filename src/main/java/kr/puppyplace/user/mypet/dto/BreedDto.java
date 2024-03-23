package kr.puppyplace.user.mypet.dto;

import kr.puppyplace.user.mypet.domain.Breed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor//@Data
public class BreedDto {

    private Long Id;

    private String breedName;

    private String breedEngName;


    public static BreedDto of(Breed Breed) {
        return BreedDto.builder()
                .Id(Breed.getId())
                .breedName(Breed.getBreedName())
                .breedEngName(Breed.getBreedEngName())
                .build();
    }
}

