package com.example.fpi.service.file;


import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.file.UserUploadFileDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {

    //    전문가 견적올리기
    List<ProUploadFileDTO> selectProUploadFileList(Long proUploadId);

    //    회원 견적 올리기
    List<UserUploadFileDTO> selectUserUploadFileList(Long userUploadId);

}
