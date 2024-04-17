package kr.puppyplace.user.mypet.service;

import jakarta.transaction.Transactional;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateResponse;

public interface MyPetService {

    MyPetCreateResponse create(MyPetCreateRequest request);

}
