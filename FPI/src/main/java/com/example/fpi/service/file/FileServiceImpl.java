package com.example.fpi.service.file;

import com.example.fpi.domain.dto.file.ProCardInfoFileDTO;
import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.file.UserUploadFileDTO;
import com.example.fpi.mapper.File.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
