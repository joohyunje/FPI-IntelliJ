package com.example.fpi.service.file;


import com.example.fpi.domain.dto.file.ProCardInfoFileDTO;
import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.file.UserUploadFileDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface FileService {

    //    전문가 견적올리기
    List<ProUploadFileDTO> selectProUploadFileList(Long proUploadId);

    //    회원 견적 올리기
    List<UserUploadFileDTO> selectUserUploadFileList(Long userUploadId);

    List<ProCardInfoFileDTO> selectProCardFileList(Long proId);

    //    전문가 마이페이지에서 자격증 사진 선택시 자격증파일 삭제
    void deleteCardPhotoFile(Long cardInfoFileId) throws IOException;

}
