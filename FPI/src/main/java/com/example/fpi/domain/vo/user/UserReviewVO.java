package com.example.fpi.domain.vo.user;

import com.example.fpi.domain.dto.user.UserReviewDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@NoArgsConstructor
@ToString
public class UserReviewVO {

    private Long userReviewId;
    private String userReviewTitle;
    private String userReviewContent;
    private Long userReviewRate;
    private Long userReviewPoint;
    private LocalDate userReviewDate;
    private Long proId;
    private String userId;

    @Builder
    public UserReviewVO(Long userReviewId, String userReviewTitle, String userReviewContent, Long userReviewRate, Long userReviewPoint, LocalDate userReviewDate, Long proId, String userId) {

        this.userReviewId = userReviewId;
        this.userReviewTitle = userReviewTitle;
        this.userReviewContent = userReviewContent;
        this.userReviewRate = userReviewRate;
        this.userReviewPoint = userReviewPoint;
        this.userReviewDate = userReviewDate;
        this.proId = proId;
        this.userId = userId;
    }

    public static UserReviewVO toEntity(UserReviewDTO userReviewDTO) {
        return UserReviewVO.builder().userReviewId(userReviewDTO.getUserReviewId())
                .userReviewTitle(userReviewDTO.getUserReviewTitle())
                .userReviewContent(userReviewDTO.getUserReviewContent())
                .userReviewRate(userReviewDTO.getUserReviewRate())
                .userReviewPoint(userReviewDTO.getUserReviewPoint())
                .userReviewDate(userReviewDTO.getUserReviewDate())
                .proId(userReviewDTO.getProId())
                .userId(userReviewDTO.getUserId())
                .build();
    }
}
