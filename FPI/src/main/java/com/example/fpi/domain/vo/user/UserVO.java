package com.example.fpi.domain.vo.user;

import com.example.fpi.domain.dto.user.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class UserVO {
    private String userId;
    private String provider;
    private String userName;
    private String phoneNumber;
    private String userImg;
    private String email;
    private Long userCash;
    private String userProApproval;
    private Long userStarRate;
    private String role;
    private Long locationId;

    @Builder
    public UserVO(String userId, String provider, String userName, String phoneNumber, String userImg, String email,
                  Long userCash, String userProApproval, Long userStarRate, String role, Long locationId){
        this.userId = userId;
        this.provider = provider;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userImg = userImg;
        this.email = email;
        this.userCash = userCash;
        this.userProApproval = userProApproval;
        this.userStarRate = userStarRate;
        this.role = role;
        this.locationId = locationId;
    }

    public static UserVO toEntity(UserDTO userDTO){
        return UserVO.builder().userId(userDTO.getUserId())
                .provider(userDTO.getProvider())
                .userName(userDTO.getUserName())
                .phoneNumber(userDTO.getPhoneNumber())
                .userImg(userDTO.getUserImg())
                .email(userDTO.getEmail())
                .userCash(userDTO.getUserCash())
                .userProApproval(userDTO.getUserProApproval())
                .userStarRate(userDTO.getUserStarRate())
                .role(userDTO.getRole())
                .locationId(userDTO.getLocationId())
                .build();

    }
}
