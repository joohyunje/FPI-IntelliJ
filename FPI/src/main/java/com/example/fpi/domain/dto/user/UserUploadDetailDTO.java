package com.example.fpi.domain.dto.user;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserUploadDetailDTO {

    private String userId;
    private Long userUploadId;
    private LocalDate userUploadDate;
    private String categoryName;
    private String serviceName;
    private String region;
    private String city;
    private String userUploadContent;
    private String userUploadTitle;
    private String userName;
    private Long userStarRate;
    private String userUploadAddress;
    private Long userUploadPay;
    private Long userUploadPayRange;

}
