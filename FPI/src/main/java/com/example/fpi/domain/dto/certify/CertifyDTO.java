package com.example.fpi.domain.dto.certify;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CertifyDTO {
    private Long proId;
    private int awardCount;
    private String proName;
    private String phoneNumber;
    private String proImg;
    private Long locationId;
    private String userId;
    private String region;
    private String city;
    private Long categoryId;
    private String cardinfoFileOriginal;
    private String cardinfoFileRoute;
    private String cardinfoFileSaved;
    private String award;

    //    배열이기때문에 컨트롤러에서 @RequestParam으로 값을 따로 받아올것임
//    private String certiOrgan;
//    private String certiNum;


}
