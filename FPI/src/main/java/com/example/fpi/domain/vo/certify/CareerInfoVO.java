package com.example.fpi.domain.vo.certify;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CareerInfoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class CareerInfoVO {
    private Long careerInfoId;
    private String award; //경력,수상내역 작성
    private Long proId;

    @Builder
    public CareerInfoVO(Long careerInfoId, String award, Long proId) {
        this.careerInfoId = careerInfoId;
        this.award = award;
        this.proId = proId;
    }

    public static CareerInfoVO toEntity(CareerInfoDTO careerInfoDTO) {
        return CareerInfoVO.builder()
                .careerInfoId(careerInfoDTO.getCareerInfoId())
                .award(careerInfoDTO.getAward())
                .proId(careerInfoDTO.getProId())
                .build();
    }


}
