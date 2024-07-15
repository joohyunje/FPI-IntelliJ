package com.example.fpi.domain.dto.certify;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CareerInfoDTO {
    private Long careerInfoId;
    private String award; //경력,수상내역 작성
    private Long proId;
}
