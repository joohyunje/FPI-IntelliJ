package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.util.PagedResponse;
import org.springframework.stereotype.Service;

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

//    유저 아이디로 전문가 아이디 가져와서 전문가페이지에서 이용

//    전문가정보 상세보기

    //    유저아이디를 입력받아 프로아이디 알아내기
    Long selectProId(String userId);

//  전문가 정보 삭제
    void deletePro(Long proId,String proName);

//    전문가 경력 가져오기
    List<ProCareerInfoListDTO> selectProCareer(Long proRequestId);

//    전문가 찾기
    PagedResponse<ProUploadListDTO> selectProUploadList(int page, int pageSize, String search);

    ProDTO detailPro(Long proId);
//  전문가 정보 삭제
//    탈퇴시 이름 비교위해서 필요
    String getProName(Long proId);
}
