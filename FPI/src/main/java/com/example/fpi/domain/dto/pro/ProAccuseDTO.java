package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProAccuseDTO {

    private Long userAccuseId;
    private String userAccuseContent;
    private String userId;
    private Long proId;
    
}
