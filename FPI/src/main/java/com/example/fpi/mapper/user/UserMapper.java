package com.example.fpi.mapper.user;

import com.example.fpi.domain.dto.user.UserReceivedReqListDTO;
import com.example.fpi.domain.dto.user.UserRequestDetailDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
import com.example.fpi.domain.dto.user.UserSendReqListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


//    유저가 받은 요청 갯수
    int countReceivedRequest();

//    유저가 보낸 요청 갯수
    int countSendRequest();


//  REST
    // 동적 쿼리
//    받은 요청 목록
    List<UserReceivedReqListDTO> selectReceivedReq(int startRow, int endRow, String sort);

//    보낸 요청 목록
    List<UserSendReqListDTO> selectSendReq(int startRow, int endRow, String sort);

//    유저가 보낸 요청 상세보기
    UserRequestDetailDTO selectUserReqDetail(Long userRequestId);

//    유저가 받은 리뷰 목록보기
    List<UserReviewListDTO> selectUserReview(String userId);

}
