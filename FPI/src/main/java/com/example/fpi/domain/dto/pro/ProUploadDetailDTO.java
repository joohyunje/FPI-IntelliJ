package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProUploadDetailDTO {

    private Long proId;
    private Long proUploadId;
    private LocalDate proUploadDate;
    private String categoryName;
    private String serviceName;
    private String region;
    private String city;
    private String proUploadContent;
    private String proUploadTitle;
    private Long empCnt;
    private String proName;
    private Long proStarRate;
    private Long proUploadPayRange;
    private Long proUploadPay;
    private String proUploadAddress;

}
