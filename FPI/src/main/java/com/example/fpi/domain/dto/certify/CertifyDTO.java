package com.example.fpi.domain.dto.certify;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CertifyDTO {
    private Long proId;
//    private int certiCount;
    private int awardCount;
    private String proName;
    private String phoneNumber;
    private String proImg;
    private Long locationId;
    private String userId;
    private String region;
    private String city;
    private Long categoryId;
    private String certiOrgan;
    private String certiNum;
    private String award;
    private String cardinfoFileOriginal;
    private String cardinfoFileRoute;
    private String cardinfoFileSaved;

}
