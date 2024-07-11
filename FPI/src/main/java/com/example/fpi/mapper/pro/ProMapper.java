package com.example.fpi.mapper.pro;

import com.example.fpi.domain.dto.pro.ProReceivedReqListDTO;
import com.example.fpi.domain.dto.pro.ProRequestDetailDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.pro.ProSendReqListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProMapper {


//    전문가가 받은 요청 갯수
    int countReceivedRequest();

//    전문가가 보낸 요청 갯수
    int countSendRequest();


//  REST
    // 동적 쿼리
//    받은 요청 목록
    List<ProReceivedReqListDTO> selectReceivedReq(int startRow, int endRow, String sort);

//    보낸 요청 목록
    List<ProSendReqListDTO> selectSendReq(int startRow, int endRow, String sort);

//    전문가가 보낸 요청 상세보기
    ProRequestDetailDTO selectProReqDetail(Long proRequestId);

//    전문가가 받은 리뷰 목록 조회
    List<ProReviewListDTO> selectProReview(Long proId);
}
