package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserSendReqListDTO {

    private Long userRequestId;
    private Long userRequestPay;
    private LocalDate userRequestDate;
    private String userRequestProgress;
    private String proUploadTitle;
    private Long proStarRate;
    private String proName;
    private Long empCnt;
    private String proImg;

}
