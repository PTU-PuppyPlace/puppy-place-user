package kr.puppyplace.user.mypet.dto;

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

}

