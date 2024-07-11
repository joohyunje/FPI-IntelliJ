package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProReceivedReqListDTO {

    private Long userRequestId;
    private Long userRequestPay;
    private LocalDate userRequestDate;
    private String userRequestProgress;
    private String userName;
    private Long userStarRate;
    private String proUploadTitle;
}
