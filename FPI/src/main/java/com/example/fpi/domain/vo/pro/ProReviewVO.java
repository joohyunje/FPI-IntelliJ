package com.example.fpi.domain.vo.pro;

import com.example.fpi.domain.dto.pro.ProReviewDTO;
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
public class ProReviewVO {

    private Long proReviewId;
    private String proReviewTitle;
    private String proReviewContent;
    private Long proReviewRate;
    private Long proReviewPoint;
    private LocalDate proReviewDate;
    private Long proId;
    private String userId;

    @Builder
    public ProReviewVO(Long proReviewId, String proReviewTitle, String proReviewContent, Long proReviewRate, Long proReviewPoint,
                       LocalDate proReviewDate, Long proId, String userId) {
        this.proReviewId = proReviewId;
        this.proReviewTitle = proReviewTitle;
        this.proReviewContent = proReviewContent;
        this.proReviewRate = proReviewRate;
        this.proReviewPoint = proReviewPoint;
        this.proReviewDate = proReviewDate;
        this.proId = proId;
        this.userId = userId;
    }

    public static ProReviewVO toEntity(ProReviewDTO proReviewDTO) {
        return ProReviewVO.builder().proReviewId(proReviewDTO.getProReviewId())
                .proReviewTitle(proReviewDTO.getProReviewTitle())
                .proReviewContent(proReviewDTO.getProReviewContent())
                .proReviewRate(proReviewDTO.getProReviewRate())
                .proReviewPoint(proReviewDTO.getProReviewPoint())
                .proReviewDate(proReviewDTO.getProReviewDate())
                .proId(proReviewDTO.getProId())
                .userId(proReviewDTO.getUserId())
                .build();
    }

}
