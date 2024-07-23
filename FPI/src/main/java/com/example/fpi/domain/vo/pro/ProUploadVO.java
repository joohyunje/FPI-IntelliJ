package com.example.fpi.domain.vo.pro;

import com.example.fpi.domain.dto.pro.ProUploadDTO;
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
public class ProUploadVO {

    private Long proUploadId;
    private String proUploadTitle;
    private String proUploadContent;
    private Long proUploadPay;
    private Long proUploadPayRange;
    private LocalDate proUploadDate;
    private String proUploadAddress;
    private Long proId;
    private Long serviceId;

    @Builder
    public ProUploadVO(Long proUploadId, String proUploadTitle, String proUploadContent, Long proUploadPay, Long proUploadPayRange, LocalDate proUploadDate,String proUploadAddress, Long proId, Long serviceId) {
        this.proUploadId = proUploadId;
        this.proUploadTitle = proUploadTitle;
        this.proUploadContent = proUploadContent;
        this.proUploadPay = proUploadPay;
        this.proUploadPayRange = proUploadPayRange;
        this.proUploadDate = proUploadDate;
        this.proUploadAddress = proUploadAddress;
        this.proId = proId;
        this.serviceId = serviceId;
    }

    public static ProUploadVO toEntity(ProUploadDTO proUploadDTO) {
        return ProUploadVO.builder().proUploadId(proUploadDTO.getProUploadId())
                .proUploadTitle(proUploadDTO.getProUploadTitle())
                .proUploadContent(proUploadDTO.getProUploadContent())
                .proUploadPay(proUploadDTO.getProUploadPay())
                .proUploadPayRange(proUploadDTO.getProUploadPayRange())
                .proUploadDate(proUploadDTO.getProUploadDate())
                .proUploadAddress(proUploadDTO.getProUploadAddress())
                .proId(proUploadDTO.getProId())
                .serviceId(proUploadDTO.getServiceId())
                .build();
    }

}
