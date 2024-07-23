package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProReviewDTO {

    private Long proReviewId;
    private String proReviewTitle;
    private String proReviewContent;
    private Long proReviewRate;
    private Long proReviewPoint;
    private LocalDate proReviewDate;
    private Long proId;
    private String userId;
}
