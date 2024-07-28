package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProRequestDetailDTO {

    private Long proId;
    private Long proRequestId;
    private LocalDate proRequestDate;
    private String categoryName;
    private String serviceName;
    private String region;
    private String city;
    private String proRequestContent;
    private String userUploadTitle;
    private Long empCnt;
    private String proName;
    private Long proStarRate;
    private String proRequestProgress;
    private Long proRequestPay;
    private Long checkProReview;
    private Long checkUserReview;
    private String proImg;


//    나중에 리뷰 부분에 다시 해도 될듯!!
//    private String userName;
//    private String proReviewTitle;
//    private String proReviewContent;
//    private Long proReviewRate;


}
