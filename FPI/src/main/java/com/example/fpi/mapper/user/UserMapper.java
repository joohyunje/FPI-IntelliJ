package com.example.fpi.mapper.user;

import com.example.fpi.domain.dto.user.*;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.pro.ProAccuseVO;
import com.example.fpi.domain.vo.pro.ProReviewVO;
import com.example.fpi.domain.vo.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    //    유저가 받은 요청 갯수
    int countReceivedRequest(String userId);

    //    유저가 보낸 요청 갯수
    int countSendRequest(String userId);


    //  REST
    // 동적 쿼리
//    받은 요청 목록
    List<UserReceivedReqListDTO> selectReceivedReq(String userId, int startRow, int endRow, String sort);

    //    보낸 요청 목록
    List<UserSendReqListDTO> selectSendReq(String userId, int startRow, int endRow, String sort);

    //    유저가 보낸 요청 상세보기
    UserRequestDetailDTO selectUserReqDetail(Long userRequestId);

    //    유저가 받은 리뷰 목록보기
    List<UserReviewListDTO> selectUserReview(String userId);

    //    회원 찾기 동적
    List<UserUploadListDTO> selectUserUploadList(int startRow, int endRow, String search, String searchType,String searchSubject);

    //    회원 찾기 갯수 동적
    int countUserUpload(String search, String searchType,String searchSubject);

    //    유저가 올리 견적 상세보기
    UserUploadDetailDTO selectUserUploadDetail(Long userUploadId);


    //    회원정보 상세보기
    UserDTO detailUser(String userId);

    //회원 탈퇴
    void deleteUser(String userId, String userName);

    //    회원가입된 유저인지 조회
    UserDTO findByUserId(String userId);

    //    OAuth회원가입
    void saveUser(UserVO vo);

    //회원가입 폼
    void updateUser(UserVO vo);

    void editApproval(String userId);

    //    유저정보 수정
    void editUser(UserVO vo);

    void editCategory(CategoryListVO vo);


    //    견적 올리기
//    시퀀스 가져오기
    long getUploadSeq();

    //    회원 견적올리기 작성하기
    void saveUserUpload(UserUploadDTO userUpload);

    //    회원 위치가져오기
    UserLocationDTO selectUserLocation(String userId);

    //    전문가 견적을 보고 요청 보내기
    void userRequest(UserRequestDTO userRequestDTO);

    // 전문가 찾기를 했을때 이미 요청을 보낸 서비스 인지 확인
    Long checkUserRequest(Long proUploadId, String userId);

    // 회원이 전문가의 리뷰 작성하기
    void userWriteProReview(ProReviewVO proReview);

    // 받은 요청 삭제
    void deleteProRequest(Long proRequestId);

    Long getReviewSeq();

    // 받은 요청 수락하기 업데이트
    void updateUserAccept(Long proRequestId);

    // 받은 요청 작업완료하기
    void updateUserComplete(Long proRequestId);

    // 전문가 신고하기
    void userAccusePro(ProAccuseVO proAccuseVO);

    //  전문가가 보낸 요청으로 proId 가져오기
    Long selectProIdByProRequestId(Long proRequestId);

    //  회원이 보낸 요청으로 proId 가져오기
    Long selectProIdByUserRequestId(Long userRequestId);

//    Long selectUserRate(String userId);
//
//    void updateUserRate(String userId, Long userStarRate);

    // 전문가의 별점 가져오기
    Long selectProRate(Long proId);

    // 전문가의 별점 업데이트
    void updateProRate(Long proId, Long proStarRate);

    // 회원이 받은 요청에서 전문가 리뷰 업데이트
    void updateProRequestProReview(Long proRequestId);

    // 회원이 보낸 요청에서 전문가 리뷰 업데이트
    void updateUserRequestProReview(Long userRequestId);

    Long selectUserReviewCnt(String userId);


//    회원 본인이 올린 견적 리스트
    List<UserUploadDetailDTO> userUploadList(String userId, int startRow, int endRow);
    int countUserUploadList(String userId);



}
