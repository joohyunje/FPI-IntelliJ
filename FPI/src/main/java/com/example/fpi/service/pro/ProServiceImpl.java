package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.dto.user.UserReviewDTO;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.domain.vo.file.ProUploadFileVO;
import com.example.fpi.mapper.File.FileMapper;
import com.example.fpi.mapper.pro.ProMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProServiceImpl implements ProService {

    private final ProMapper proMapper;
    private final FileMapper fileMapper;

    //    받은 요청 목록
    @Override
    public PagedResponse<ProReceivedReqListDTO> selectReceivedReq(Long proId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countReceivedRequest(proId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);

        List<ProReceivedReqListDTO> requests = proMapper.selectReceivedReq(proId, startRow, endRow, sort);

        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }

    //  보낸 요청 목록
    @Override
    public PagedResponse<ProSendReqListDTO> selectSendReq(Long proId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countSendRequest(proId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);

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
    @Transactional
    public void saveProUpload(ProUploadDTO proUpload, List<MultipartFile> files) {
        Long proUploadId = proMapper.getUploadSeq();
        proUpload.setProUploadId(proUploadId);
        proMapper.saveProUpload(proUpload);

        saveProUploadFile(proUploadId, files);
    }

    @Override
    public void saveProUploadFile(Long proUploadId, List<MultipartFile> files) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);

        for (MultipartFile file : files) {
            // 방어코드
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename();
            String proUploadFileRouteName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;

            try {
                // 파일 저장 경로 설정
                Path directoryPath = Paths.get("src/main/resources/static/uploads/proUpload/" + datePath);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath); // 폴더가 없으면 생성
                }
                Path filePath = directoryPath.resolve(proUploadFileRouteName);
                // 파일 저장
                Files.copy(file.getInputStream(), filePath);

                ProUploadFileDTO proUploadFileDTO = new ProUploadFileDTO();
                proUploadFileDTO.setProUploadId(proUploadId);
                proUploadFileDTO.setProUploadFileOriginal(originalFileName);
                proUploadFileDTO.setProUploadFileRoute("/uploads/proUpload/" + datePath + "/" + proUploadFileRouteName);

                fileMapper.insertProUploadFile(ProUploadFileVO.toEntity(proUploadFileDTO)); // 파일 정보 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ProLocationDTO selectProLocation(Long proId) {
        return proMapper.selectProLocation(proId);
    }

    @Override
    public void proRequest(ProRequestDTO proRequestDTO) {
        proMapper.proRequest(proRequestDTO);
    }

    @Override
    public Long checkProRequest(Long userUploadId, Long proId) {
        return proMapper.checkProRequest(userUploadId, proId);
    }

    @Override
    public void proWriteUserReview(UserReviewDTO userReview) {
        proMapper.proWriteUserReview(userReview);
    }

    @Override
    public void deleteUserRequest(Long userRequestId) {
        proMapper.deleteUserRequest(userRequestId);
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
        int totalPages = (int) Math.ceil((double) totalUploads / pageSize);

        List<ProUploadListDTO> uploads = proMapper.selectProUploadList(startRow, endRow, search);

        return new PagedResponse<>(uploads, page, totalPages, pageSize, totalUploads);
    }


}
