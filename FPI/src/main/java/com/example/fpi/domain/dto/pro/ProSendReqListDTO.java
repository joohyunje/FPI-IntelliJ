package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProSendReqListDTO {

    private Long proRequestId;
    private Long proRequestPay;
    private LocalDate proRequestDate;
    private String proRequestProgress;
    private String userUploadTitle;
    private Long userStarRate;
    private String userName;

}
