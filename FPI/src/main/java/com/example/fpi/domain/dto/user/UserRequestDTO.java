package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserRequestDTO {

    private String userRequestId;
    private Long userRequestPay;
    private String userRequestContent;
    private LocalDate userRequestDate;
    private String userRequestProgress;
    private String userId;
    private Long proUploadId;

}
