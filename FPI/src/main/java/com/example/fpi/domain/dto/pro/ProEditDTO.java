package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProEditDTO {
    private Long proId;
    private String proName;
    private String phoneNumber;
    private String proImg;
    private Long locationId;
    private String region;
    private String city;
    private String email;
//    private String award;
    private Long categoryId;
    private Long careerInfoId;
    private Long cardInfoId;
}
