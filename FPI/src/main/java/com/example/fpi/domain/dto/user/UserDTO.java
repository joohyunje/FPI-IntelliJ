package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private String userId;
    private String provider;
    private String userName;
    private String phoneNumber;
    private String userImg;
    private String email;
    private int userCash;
    private String userProApproval;
    private Long userStarRate;
    private String role;
    private Long locationId;
    private Long categoryId;
    private String categoryName;
    private String region;
    private String city;

}
