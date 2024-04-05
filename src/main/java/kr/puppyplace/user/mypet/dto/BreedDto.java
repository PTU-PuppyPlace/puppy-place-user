package kr.puppyplace.user.mypet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BreedDto {

    private Long id;

    private String name;

    private String engName;

}

