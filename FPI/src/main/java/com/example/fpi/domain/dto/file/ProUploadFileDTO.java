package com.example.fpi.domain.dto.file;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProUploadFileDTO {

    private Long proUploadFileId;
    private String proUploadFileRoute;
    private String proUploadFileOriginal;
    private String proUploadFileSaved;
    private Long proUploadId;

}
