package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserReviewDTO {

    private Long userReviewId;
    private String userReviewTitle;
    private String userReviewContent;
    private Long userReviewRate;
    private Long userReviewPoint;
    private LocalDate userReviewDate;
    private Long proId;
    private String userId;
}
