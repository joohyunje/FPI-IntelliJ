package com.example.fpi.mapper.file;

import com.example.fpi.domain.vo.file.ProUploadFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    void insertProUploadFile(ProUploadFileVO proUploadFileVO);
}
