package kr.puppyplace.user.mypet.controller;

import java.util.List;
import kr.puppyplace.user.mypet.dto.BreedDto;
import kr.puppyplace.user.mypet.service.BreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BreedController {

    private final BreedService breedService;

    @GetMapping(value = "/breeds/search")
    public ResponseEntity<List<BreedDto>> getSearchedBreed(
            @RequestParam(value = "breedKey") String breedKey) {
        return ResponseEntity.ok().body(breedService.findByBreedNameContaining(breedKey));
    }

}
