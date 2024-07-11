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

    private String proRequestId;
    private Long proRequestPay;
    private String proRequestContent;
    private LocalDate proRequestDate;
    private String proRequestProgress;
    private Long proId;
    private Long userUploadId;

    @Builder
    public ProRequestVO(String proRequestId,Long proRequestPay, String proRequestContent, LocalDate proRequestDate, String proRequestProgress, Long proId, Long userUploadId) {
        this.proRequestId = proRequestId;
        this.proRequestPay = proRequestPay;
        this.proRequestContent = proRequestContent;
        this.proRequestDate = proRequestDate;
        this.proRequestProgress = proRequestProgress;
        this.proId = proId;
        this.userUploadId = userUploadId;
    }

    public static ProRequestVO toEntity(ProRequestDTO proRequestDTO){
        return ProRequestVO.builder().proRequestId(proRequestDTO.getProRequestId())
                .proRequestPay(proRequestDTO.getProRequestPay())
                .proRequestContent(proRequestDTO.getProRequestContent())
                .proRequestDate(proRequestDTO.getProRequestDate())
                .proRequestProgress(proRequestDTO.getProRequestProgress())
                .proId(proRequestDTO.getProId())
                .userUploadId(proRequestDTO.getUserUploadId())
                .build();
    }

}
