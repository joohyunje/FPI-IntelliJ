package com.example.fpi.mapper.pro;

import com.example.fpi.domain.dto.pro.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProMapper {


//    전문가가 받은 요청 갯수
    int countReceivedRequest(Long proId);

//    전문가가 보낸 요청 갯수
    int countSendRequest(Long proId);


//  REST
    // 동적 쿼리
//    받은 요청 목록
    List<ProReceivedReqListDTO> selectReceivedReq(Long proId, int startRow, int endRow, String sort);

//    보낸 요청 목록
    List<ProSendReqListDTO> selectSendReq(Long proId, int startRow, int endRow, String sort);

//    전문가가 보낸 요청 상세보기
    ProRequestDetailDTO selectProReqDetail(Long proRequestId);

//    전문가가 받은 리뷰 목록 조회
    List<ProReviewListDTO> selectProReview(Long proId);


    //    <!--    마이페이지 전문가정보 상세보기-->
    ProDTO detailPro(Long proId);

//    유저아이디를 입력받아 프로아이디 알아내기
    Long selectProId(String userId);

    //    전문가 탈퇴
    void deletePro(Long proId,String proName);

    //    요청으로 전문가 경력 가져오기
    List<ProCareerInfoListDTO> selectProCareerByReq(Long proRequestId);

    //  올린견적으로  전문가 경력 가져오기
    List<ProCareerInfoListDTO> selectProCareerByUp(Long proUploadId);

    //    전문가가 올리 견적 상세보기
    ProUploadDetailDTO selectProUploadDetail(Long proUploadId);

    //    견적 올리기
//    시퀀스 가져오기
    long getUploadSeq();

//    전문가 견적올리기 작성하기
    void saveProUpload(ProUploadDTO proUpload);

//    전뭄가 찾기 동적
    List<ProUploadListDTO> selectProUploadList(int startRow, int endRow, String search);

//    전문가 찾기 갯수 동적
    int countProUpload(String search);
//    탈퇴시 전문가 이름필요
    String selectProName(Long proId);
}
