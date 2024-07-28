package com.example.fpi.domain.vo.pro;

import com.example.fpi.domain.dto.pro.ProRequestDTO;
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
public class ProRequestVO {

    private Long proRequestId;
    private Long proRequestPay;
    private String proRequestContent;
    private LocalDate proRequestDate;
    private String proRequestProgress;
    private Long proId;
    private Long userUploadId;
    private Long checkProReview;
    private Long checkUserReview;

    @Builder
    public ProRequestVO(Long proRequestId, Long proRequestPay, String proRequestContent, LocalDate proRequestDate, String proRequestProgress, Long proId, Long userUploadId, Long checkProReview, Long checkUserReview) {
        this.proRequestId = proRequestId;
        this.proRequestPay = proRequestPay;
        this.proRequestContent = proRequestContent;
        this.proRequestDate = proRequestDate;
        this.proRequestProgress = proRequestProgress;
        this.proId = proId;
        this.userUploadId = userUploadId;
        this.checkProReview = checkProReview;
        this.checkUserReview = checkUserReview;
    }

    public static ProRequestVO toEntity(ProRequestDTO proRequestDTO) {
        return ProRequestVO.builder().proRequestId(proRequestDTO.getProRequestId())
                .proRequestPay(proRequestDTO.getProRequestPay())
                .proRequestContent(proRequestDTO.getProRequestContent())
                .proRequestDate(proRequestDTO.getProRequestDate())
                .proRequestProgress(proRequestDTO.getProRequestProgress())
                .proId(proRequestDTO.getProId())
                .userUploadId(proRequestDTO.getUserUploadId())
                .checkProReview(proRequestDTO.getCheckProReview())
                .checkUserReview(proRequestDTO.getCheckUserReview())
                .build();
    }

}
