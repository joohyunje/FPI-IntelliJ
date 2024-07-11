package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserReviewListDTO {

    private Long proId;
    private String userId;
    private String proName;
    private String userReviewTitle;
    private String userReviewContent;
    private String userReviewRate;
    private LocalDate userReviewDate;

}
