package com.example.fpi.service.file;

import com.example.fpi.domain.dto.file.ProCardInfoFileDTO;
import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.file.UserUploadFileDTO;
import com.example.fpi.mapper.File.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Override
    public List<ProUploadFileDTO> selectProUploadFileList(Long proUploadId) {
        return fileMapper.selectProUploadFileList(proUploadId);
    }

    @Override
    public List<UserUploadFileDTO> selectUserUploadFileList(Long userUploadId) {
        return fileMapper.selectUserUploadFileList(userUploadId);
    }

    @Override
    public List<ProCardInfoFileDTO> selectProCardFileList(Long proId) {
        return fileMapper.selectProCardFileList(proId);
    }

    //    전문가 마이페이지에서 자격증 사진 선택시 자격증파일 삭제
    @Override
    public void deleteCardPhotoFile(Long cardInfoFileId) throws IOException {
        //전문가 탈퇴,탈퇴시 서버에 저장된 프로필사진도 함께삭제
        Path cardPhoto = Paths.get("src/main/resources/static" + fileMapper.cardFindImg(cardInfoFileId).getCardInfoFileRoute());

        if (Files.exists(cardPhoto)) {
            Files.delete(cardPhoto);
        }
        fileMapper.deleteCardPhotoFile(cardInfoFileId);
    }

}
