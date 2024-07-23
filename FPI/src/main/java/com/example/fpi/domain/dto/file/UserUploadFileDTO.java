package com.example.fpi.domain.dto.file;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserUploadFileDTO {

    private Long userUploadFileId;
    private String userUploadFileRoute;
    private String userUploadFileOriginal;
    private Long userUploadId;

}
