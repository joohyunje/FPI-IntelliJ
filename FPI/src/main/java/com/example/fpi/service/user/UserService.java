package com.example.fpi.service.user;

import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.pro.ProReviewDTO;
import com.example.fpi.domain.dto.user.*;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.domain.vo.user.UserVO;
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
    void deleteUser(String userId,String userName);

//    전문가 탈퇴시 approval변경
    void editApproval(String userId);

    //    회원 찾기
    PagedResponse<UserUploadListDTO> selectUserUploadList(int page, int pageSize, String search);

    //    유저가 올리 견적 상세보기
    UserUploadDetailDTO selectUserUploadDetail(Long userUploadId);

    void updateCash(String userId,int cash);

    //회원 견적 올리기
    void saveUserUpload(UserUploadDTO userUpload, List<MultipartFile> files);

    void saveUserUploadFile(Long userUploadId, List<MultipartFile> files);

    //    회원 위치가져오기
    UserLocationDTO selectUserLocation(String userId);

    void userRequest(UserRequestDTO userRequestDTO);

    Long checkUserRequest(Long proUploadId, String userId);

    void userWriteProReview(ProReviewDTO proReview);

    void deleteProRequest(Long proRequestId);
}
