package com.example.fpi.domain.vo.user;

import com.example.fpi.domain.dto.user.UserUploadDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@ToString
@NoArgsConstructor
public class UserUploadVO {

    private Long userUploadId;
    private String userUploadTitle;
    private String userUploadContent;
    private Long userUploadPay;
    private Long userUploadPayRange;
    private LocalDate userUploadDate;
    private String userUploadAddress;
    private String userId;

    @Builder
    public UserUploadVO(Long userUploadId, String userUploadTitle, String userUploadContent, Long userUploadPay, Long userUploadPayRange, LocalDate userUploadDate, String userUploadAddress, String userId) {
        this.userUploadId = userUploadId;
        this.userUploadTitle = userUploadTitle;
        this.userUploadContent = userUploadContent;
        this.userUploadPay = userUploadPay;
        this.userUploadPayRange = userUploadPayRange;
        this.userUploadDate = userUploadDate;
        this.userUploadAddress = userUploadAddress;
        this.userId = userId;
    }

    public static UserUploadVO toEntity(UserUploadDTO userUploadDTO) {
        return UserUploadVO.builder().userUploadId(userUploadDTO.getUserUploadId())
                .userUploadTitle(userUploadDTO.getUserUploadTitle())
                .userUploadContent(userUploadDTO.getUserUploadContent())
                .userUploadPay(userUploadDTO.getUserUploadPay())
                .userUploadPayRange(userUploadDTO.getUserUploadPayRange())
                .userUploadDate(userUploadDTO.getUserUploadDate())
                .userUploadAddress(userUploadDTO.getUserUploadAddress())
                .userId(userUploadDTO.getUserId())
                .build();
    }

}
