package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CardInfoFileDTO;
import com.example.fpi.domain.dto.certify.CareerInfoDTO;
import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.domain.vo.certify.CardInfoFileVO;
import com.example.fpi.domain.vo.certify.CardInfoVO;
import com.example.fpi.domain.vo.certify.CareerInfoVO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.pro.ProVO;
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
    void deletePro(Long proId,String proName) throws IOException;
// 함께 저장된 자격증 사진 삭제
    void deleteCardFile(Long cardInfoId) throws IOException;


    //  올린견적으로  전문가 경력 가져오기
    List<ProCareerInfoListDTO> selectProCareerByUp(Long proUploadId);

//   요청으로 전문가 경력 가져오기
    List<ProCareerInfoListDTO> selectProCareerByReq(Long proRequestId);

//    전문가 찾기
    PagedResponse<ProUploadListDTO> selectProUploadList(int page, int pageSize, String search);

//    전문가 상세보기
    ProDTO detailPro(Long proId);
    List<CardInfoFileDTO> selectCardInfoFile(Long proId);
    List<CardInfoDTO> selectCard(Long proId);
//   전문가 수정정보 뿌려줌
    ProEditDTO selectEditPro(Long proId);
//    전문가 수정하기 폼
    void updatePro(ProEditDTO dto,List<MultipartFile> files,MultipartFile proProfile) throws IOException;
    void editPro(String proName,String phoneNumber,String proImg,Long locationId,Long proId);
    void editCategory(Long categoryId,Long proId);
    void editCardInfoFile(CardInfoFileDTO dto);
    void editCardInfo(CardInfoDTO dto);
    void editCareerInfo(Long careerInfoId,String award);

    String editProImage(Long proId,MultipartFile proProfile) throws IOException;  //전문가 활동사진 파일수정



    //  전문가 정보 삭제
//    탈퇴시 이름 비교위해서 필요
    String getProName(Long proId);

    //    전문가가 올리 견적 상세보기
    ProUploadDetailDTO selectProUploadDetail(Long proUploadId);

//    견적 올리기
//    시퀀스 가져오기
    void saveProUpload(ProUploadDTO proUpload);
}
