package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserRequestDetailDTO {
    private String userId;
    private Long userRequestId;
    private LocalDate userRequestDate;
    private String categoryName;
    private String serviceName;
    private String region;
    private String city;
    private String userRequestContent;
    private String proUploadTitle;
    private String userName;
    private Long userStarRate;
    private String userRequestProgress;
    private Long userRequestPay;
    private Long proUploadId;
    private Long checkProReview;
    private Long checkUserReview;
    private String userImg;
}
