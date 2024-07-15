package com.example.fpi.domain.dto.certify;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CertifyDTO {
    private String userId;
    private Long proId;
    private String proName;
    private String phoneNumber;
    private String proImg;
    private Long locationId;
    private String region;
    private String city;
    private Long categoryId;
    private String certiOrgan;
    private String certiNum;
    private String award;

}
