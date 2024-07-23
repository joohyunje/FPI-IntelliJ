package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserLocationDTO {

    private String region;
    private String city;
    private String userName;

}
