package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CardInfoFileDTO;
import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.dto.user.UserAccuseDTO;
import com.example.fpi.domain.dto.user.UserReviewDTO;
import com.example.fpi.domain.util.PagedResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProService {

    //    REST
    // 동적 쿼리
//    받은 요청 목록
    PagedResponse<ProReceivedReqListDTO> selectReceivedReq(Long proId, int page, int pageSize, String sort);

    //    보낸 요청 목록
    PagedResponse<ProSendReqListDTO> selectSendReq(Long proId, int page, int pageSize, String sort);

    ProRequestDetailDTO selectProReqDetail(Long proRequestId);

    //    전문가가 받은 리뷰 목록
    List<ProReviewListDTO> selectProReview(Long proId);


    //    유저아이디를 입력받아 프로아이디 알아내기
    Long selectProId(String userId);


    //  전문가 정보 삭제
    void deletePro(Long proId, String proName) throws IOException;

    // 함께 저장된 자격증 사진 삭제
    void deleteCardFile(Long proId) throws IOException;

// 파일아이디 가져와서 삭제
//    Long selectCardFileId(Long proId);


    //  올린견적으로  전문가 경력 가져오기
    List<ProCareerInfoListDTO> selectProCareerByUp(Long proUploadId);

    //   요청으로 전문가 경력 가져오기
    List<ProCareerInfoListDTO> selectProCareerByReq(Long proRequestId);

    //    전문가 찾기
    PagedResponse<ProUploadListDTO> selectProUploadList(int page, int pageSize, String search, String searchType);


    //    전문가 상세보기
    ProDetailDTO detailPro(Long proId);

    List<CardInfoFileDTO> selectCardInfoFile(Long proId);

    List<CardInfoDTO> selectCard(Long proId);
//    List<CardInfoFIleDTO> cardFileList(Long proId);


    //   전문가 수정정보 뿌려줌
    ProEditDTO selectEditPro(Long proId);

    //    전문가 수정하기 폼
    void updatePro(ProEditDTO dto, List<CardInfoDTO> cards, List<MultipartFile> files, MultipartFile proProfile) throws IOException;

    void editPro(String proName, String phoneNumber, String proImg, Long locationId, Long proId);

    void editCategory(Long categoryId, Long proId);

    void editCardInfoFile(Long proId, List<MultipartFile> files) throws IOException;

    void editCardInfo(List<CardInfoDTO> cards);

    void editCareerInfo(Long careerInfoId, String award);


    String editProImage(Long proId, MultipartFile proProfile) throws IOException;  //전문가 활동사진 파일수정


    //  전문가 정보 삭제
//    탈퇴시 이름 비교위해서 필요
    String getProName(Long proId);

    //    전문가가 올리 견적 상세보기
    ProUploadDetailDTO selectProUploadDetail(Long proUploadId);

    //    견적 올리기
//    시퀀스 가져오기
    void saveProUpload(ProUploadDTO proUpload, List<MultipartFile> files);

    void saveProUploadFile(Long proUploadId, List<MultipartFile> files);

    //    전문가 위치가져오기
    ProLocationDTO selectProLocation(Long proId);

    //    전문가 견적을 보고 요청 보내기
    void proRequest(ProRequestDTO proRequestDTO);

    Long checkProRequest(Long userUploadId, Long proId);

    void proWriteUserReview(UserReviewDTO userReview);

    void deleteUserRequest(Long userRequestId);


    void updateProAccept(Long userRequestId);

    void updateProComplete(Long userRequestId);

    void proAccuseUser(UserAccuseDTO userAccuseDTO);

    String selectUserIdByUserRequestId(Long userRequestId);

    //    컨트롤러에서 생성하여 전문가 정보 수정시 input 추가되었을때
    List<CardInfoDTO> getCardInfoList(Long proId, String cardInfoId, String certiOrgan, String certiNum);

    Long selectProIdByProUploadId(Long proUploadId);

//    Long selectProRate(Long proId);
//
//    void updateProRate(Long proId, Long proStarRate);

    Long selectUserRate(String userId);

    void updateUserRate(String userId, Long userStarRate);

    String selectUserIdByProRequestId(Long proRequestId);

    void updateUserRequestUserReview(Long userRequestId);

    void updateProRequestUserReview(Long proRequestId);

    void updateEmpCnt(Long proId, Long empCnt);

    Long empCount(Long proId);

    //    마이페이지에서 클릭으로 자격증정보 삭제
    void clickDeleteCard(Long cardInfoId);

    Long selectProReviewCnt(Long proId);

}
