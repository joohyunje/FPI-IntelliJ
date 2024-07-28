package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserReceivedReqListDTO {

    private Long proRequestId;
    private Long proRequestPay;
    private LocalDate proRequestDate;
    private String proRequestProgress;
    private String proName;
    private Long proStarRate;
    private Long empCnt;
    private String userUploadTitle;
    private String proImg;
}
