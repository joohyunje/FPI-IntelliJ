package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.pro.ProReceivedReqListDTO;
import com.example.fpi.domain.dto.pro.ProRequestDetailDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.pro.ProSendReqListDTO;
import com.example.fpi.domain.util.PagedResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProService {

//    REST
    // 동적 쿼리
//    받은 요청 목록
    PagedResponse<ProReceivedReqListDTO> selectReceivedReq(int page, int pageSize, String sort);

//    보낸 요청 목록
    PagedResponse<ProSendReqListDTO> selectSendReq(int page, int pageSize, String sort);

    ProRequestDetailDTO selectProReqDetail(Long proRequestId);

//    전문가가 받은 리뷰 목록
    List<ProReviewListDTO> selectProReview(Long proId);
}
