package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProLocationDTO {

    private String region;
    private String city;
    private String proName;

}
