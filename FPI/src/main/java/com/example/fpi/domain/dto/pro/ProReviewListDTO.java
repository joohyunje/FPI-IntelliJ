package com.example.fpi.domain.dto.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class ProReviewListDTO {

    private Long proId;
    private String userId;
    private String userName;
    private String proReviewTitle;
    private String proReviewContent;
    private String proReviewRate;
    private LocalDate proReviewDate;
    private Long proReviewId;
    private String proName;
    private Long RN;

}
