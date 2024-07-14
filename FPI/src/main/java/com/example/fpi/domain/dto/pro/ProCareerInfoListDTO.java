package com.example.fpi.domain.dto.pro;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProCareerInfoListDTO {

    private Long proCareerInfoId;
    private String award;
    private Long proId;

}
