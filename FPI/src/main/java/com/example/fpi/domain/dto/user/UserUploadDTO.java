package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserUploadDTO {

    private Long userUploadId;
    private String userUploadTitle;
    private String userUploadContent;
    private Long userUploadPay;
    private Long userUploadPayRange;
    private LocalDate userUploadDate;
    private String userUploadAddress;
    private String userId;
}
