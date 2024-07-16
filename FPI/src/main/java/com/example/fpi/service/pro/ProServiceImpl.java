package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.pro.*;
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
    public PagedResponse<ProReceivedReqListDTO> selectReceivedReq(Long proId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countReceivedRequest(proId);
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<ProReceivedReqListDTO> requests = proMapper.selectReceivedReq(proId, startRow, endRow, sort);

        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }
    //  보낸 요청 목록
    @Override
    public PagedResponse<ProSendReqListDTO> selectSendReq(Long proId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countSendRequest(proId);
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<ProSendReqListDTO> requests = proMapper.selectSendReq(proId, startRow, endRow, sort);

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

    //전문가전환시 유저아이디통해서 전문가아이디 가져옴
    @Override
    public Long selectProId(String userId) {
        return proMapper.selectProId(userId);
    }



    //    전문가 삭제 시, 이름 입력 비교를 위해
//    DB에서 이름을 가져오기




    //    전문가 상세정보 조회
    @Override
    public ProDTO detailPro(Long proId) {
        return proMapper.detailPro(proId);

    }

    //전문가 정보 삭제
    @Override
    public void deletePro(Long proId, String proName) {

        proMapper.deletePro(proId, proName);
    }



    // 전문가 삭제시 이름비교 위해 필요
    @Override
    public String getProName(Long proId) {
        return proMapper.selectProName(proId);
    }

//    전문가가 올린 견적 상세보기
    @Override
    public ProUploadDetailDTO selectProUploadDetail(Long proUploadId) {
        return proMapper.selectProUploadDetail(proUploadId);
    }

//  전문가 견적 작성하기
    @Override
    public void saveProUpload(ProUploadDTO proUpload) {
        Long proUploadId = proMapper.getUploadSeq();
        proUpload.setProUploadId(proUploadId);
        proMapper.saveProUpload(proUpload);
    }

    //    올린 견적으로 경력 가져오기
    @Override
    public List<ProCareerInfoListDTO> selectProCareerByUp(Long proUploadId) {
        return proMapper.selectProCareerByUp(proUploadId);
    }

    //  요청으로  전문가 경력 가져오기
    @Override
    public List<ProCareerInfoListDTO> selectProCareerByReq(Long proRequestId) {
        return proMapper.selectProCareerByReq(proRequestId);
    }

//  전문가 찾기
    @Override
    public PagedResponse<ProUploadListDTO> selectProUploadList(int page, int pageSize, String search) {

        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalUploads = proMapper.countProUpload(search);
        int totalPages = (int) Math.ceil((double)totalUploads/pageSize);

        List<ProUploadListDTO> uploads = proMapper.selectProUploadList(startRow, endRow, search);

        return new PagedResponse<>(uploads, page, totalPages, pageSize, totalUploads);
    }


}
