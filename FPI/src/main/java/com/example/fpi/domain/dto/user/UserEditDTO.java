package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserEditDTO {
    private String userId;
    private String userName;
    private String phoneNumber;
    private String userImg;
    private String email;
    private Long categoryId;
    private String region;
    private String city;

}
