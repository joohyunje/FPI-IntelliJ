package com.example.fpi.service.user;

import com.example.fpi.domain.dto.user.*;
import com.example.fpi.domain.util.PagedResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

//    REST
    // 동적 쿼리
//    받은 요청 목록
    PagedResponse<UserReceivedReqListDTO> selectReceivedReq(int page, int pageSize, String sort);

//    보낸 요청 목록
    PagedResponse<UserSendReqListDTO> selectSendReq(int page, int pageSize, String sort);

    //    유저가 보낸 요청 상세보기
    UserRequestDetailDTO selectUserReqDetail(Long userRequestId);

    //    유저가 받은 리뷰 목록보기
    List<UserReviewListDTO> selectUserReview(String userId);

//    유저정보 상세보기
    UserDTO detailUser(String userId);
//    유저 정보 삭제
    void deleteUser(String userId,String userName);
}
