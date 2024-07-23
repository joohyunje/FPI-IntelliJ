package com.example.fpi.domain.dto.main;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LocationDTO {
    private Long locationId;
    private String region;
    private String city;
}
