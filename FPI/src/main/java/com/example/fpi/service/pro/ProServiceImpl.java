package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.pro.ProReceivedReqListDTO;
import com.example.fpi.domain.dto.pro.ProRequestDetailDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.pro.ProSendReqListDTO;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.mapper.pro.ProMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProServiceImpl implements ProService {

    private final ProMapper proMapper;

    //    받은 요청 목록
    @Override
    public PagedResponse<ProReceivedReqListDTO> selectReceivedReq(int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countReceivedRequest();
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<ProReceivedReqListDTO> requests = proMapper.selectReceivedReq(startRow, endRow, sort);

        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }
    //  보낸 요청 목록
    @Override
    public PagedResponse<ProSendReqListDTO> selectSendReq(int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countSendRequest();
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<ProSendReqListDTO> requests = proMapper.selectSendReq(startRow, endRow, sort);

        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }

    @Override
    public ProRequestDetailDTO selectProReqDetail(Long proRequestId) {
        return proMapper.selectProReqDetail(proRequestId);
    }

    @Override
    public List<ProReviewListDTO> selectProReview(Long proId) {
        return proMapper.selectProReview(proId);
    }
}
