package kr.puppyplace.user.mypet.service;

import jakarta.transaction.Transactional;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateResponse;

public interface MyPetService  {

    /**
     * mypage에서 반려동물 등록을 수행하는 메소드.
     *
     * @param request 반려동물 등록 요청
     * @return 반려동물 등록 결과 (myPage)
     */
    @Transactional
    MyPetCreateResponse myPetCreate(MyPetCreateRequest request);
}
