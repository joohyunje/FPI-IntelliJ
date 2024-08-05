package com.example.fpi.service.user;

import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.pro.ProAccuseDTO;
import com.example.fpi.domain.dto.pro.ProReviewDTO;
import com.example.fpi.domain.dto.user.*;
import com.example.fpi.domain.util.PagedResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UserService {

    //    REST
    // 동적 쿼리
//    받은 요청 목록
    PagedResponse<UserReceivedReqListDTO> selectReceivedReq(String userId, int page, int pageSize, String sort);

    //    보낸 요청 목록
    PagedResponse<UserSendReqListDTO> selectSendReq(String userId, int page, int pageSize, String sort);

    //    유저가 보낸 요청 상세보기
    UserRequestDetailDTO selectUserReqDetail(Long userRequestId);

    //    유저가 받은 리뷰 목록보기
    List<UserReviewListDTO> selectUserReview(String userId);

    //    유저정보 상세보기
    UserDTO detailUser(String userId);

    //    유저 삭제 시, 이름 입력 비교를 위해
//    DB에서 이름을 가져오기
    String getUserName(String userId);

    //    유저 정보수정
    void editUser(UserDTO dto);

    void editCategory(CategoryListDTO dto);

    //    유저 정보 삭제
    void deleteUser(String userId, String userName);

    //    전문가 탈퇴시 approval변경
    void editApproval(String userId);

    //    회원 찾기
    PagedResponse<UserUploadListDTO> selectUserUploadList(int page, int pageSize, String search, String searchType);

    //    유저가 올리 견적 상세보기
    UserUploadDetailDTO selectUserUploadDetail(Long userUploadId);


    //회원 견적 올리기
    void saveUserUpload(UserUploadDTO userUpload, List<MultipartFile> files);

    void saveUserUploadFile(Long userUploadId, List<MultipartFile> files);

    //    회원 위치가져오기
    UserLocationDTO selectUserLocation(String userId);

    //    전문가 견적을 보고 요청 보내기
    void userRequest(UserRequestDTO userRequestDTO);

    // 전문가 찾기를 했을때 이미 요청을 보낸 서비스 인지 확인
    Long checkUserRequest(Long proUploadId, String userId);

    // 회원이 전문가의 리뷰 작성하기
    void userWriteProReview(ProReviewDTO proReview);

    // 받은 요청 삭제
    void deleteProRequest(Long proRequestId);

    // 받은 요청 수락하기 업데이트
    void updateUserAccept(Long proRequestId);

    // 받은 요청 작업완료하기
    void updateUserComplete(Long proRequestId);

    // 전문가 신고하기
    void userAccusePro(ProAccuseDTO proAccuseDTO);

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

}
