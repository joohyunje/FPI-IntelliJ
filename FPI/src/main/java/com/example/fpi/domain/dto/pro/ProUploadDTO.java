package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProUploadDTO {

    private Long proUploadId;
    private String proUploadTitle;
    private String proUploadContent;
    private Long proUploadPay;
    private Long proUploadPayRange;
    private LocalDate proUploadDate;
    private String proUploadAddress;
    private Long proId;
}
