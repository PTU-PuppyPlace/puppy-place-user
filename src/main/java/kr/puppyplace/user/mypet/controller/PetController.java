package kr.puppyplace.user.mypet.controller;

import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateResponse;
import kr.puppyplace.user.mypet.service.MyPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetController {

    private final MyPetService myPetService;

    @PostMapping(value = "/mypet")
    public ResponseEntity<MyPetCreateResponse> myPetCreate(
            @RequestBody MyPetCreateRequest request) {
        return ResponseEntity
                .ok()
                .body(myPetService.myPetCreate(request));
    }
}
