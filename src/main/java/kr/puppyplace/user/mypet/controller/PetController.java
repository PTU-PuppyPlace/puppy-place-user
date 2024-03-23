package kr.puppyplace.user.mypet.controller;

import java.util.List;
import kr.puppyplace.user.mypet.dto.BreedDto;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateResponse;
import kr.puppyplace.user.mypet.service.BreedService;
import kr.puppyplace.user.mypet.service.MyPetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PetController {

    private final BreedService breedService;
    private final MyPetService myPetService;

    @GetMapping(value = "/breeds/search")
    public ResponseEntity<List<BreedDto>> getSearchedBreed(
        @RequestParam(value = "breedKey") String breedKey) {
        log.info("breedKey : " + breedKey);
        return ResponseEntity.ok().body(breedService.findByBreedNameContaining(breedKey));
    }

    @PostMapping(value = "/mypet")
    public ResponseEntity<MyPetCreateResponse> myPetCreate(
        @RequestBody MyPetCreateRequest request) {
        log.info("request : " + request);

        return ResponseEntity.ok().body(myPetService.myPetCreate(request));
    }
}
