package kr.puppyplace.user.mypet.dto;

import kr.puppyplace.user.mypet.domain.Breed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor//@Data
public class BreedDto {

    private Long id;

    private String breedName;

    private String breedEngName;

    public static BreedDto of(Breed breed) {
        return BreedDto.builder()
            .id(breed.getId())
            .breedName(breed.getBreedName())
            .breedEngName(breed.getBreedEngName())
            .build();
    }
}

