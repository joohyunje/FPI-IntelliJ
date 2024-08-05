package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProUploadListDTO {

    private Long proUploadId;
    private Long proId;
    private String proUploadTitle;
    private Long proStarRate;
    private LocalDate proUploadDate;
    private String proName;
    private String serviceName;
    private String proImg;

    private String proUploadFileRoute;
    private Long reviewCount;

}
