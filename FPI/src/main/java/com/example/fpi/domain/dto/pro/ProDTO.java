package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProDTO {
    private Long proId;
    private String proName;
    private String phoneNumber;
    private String proImg;
    private Long proStarRate;
    private Long empCnt;
    private Long locationId;
    private String userId;
    private Long categoryId;
    private String categoryName;
    private String region;
    private String city;
    private String email;
    private String userCash;
    private String award;
}
