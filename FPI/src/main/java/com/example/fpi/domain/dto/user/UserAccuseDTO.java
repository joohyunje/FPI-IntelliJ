package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserAccuseDTO {

    private Long userAccuseId;
    private String userAccuseContent;
    private String userId;
    private Long proId;

}
