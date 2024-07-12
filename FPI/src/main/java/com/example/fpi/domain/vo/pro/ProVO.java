package com.example.fpi.domain.vo.pro;

import com.example.fpi.domain.dto.pro.ProDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class ProVO {
    private Long proId;
    private String proName;
    private String phoneNumber;
    private String proImg;
    private Long proStarRate;
    private Long empCnt;
    private Long locationId;
    private String userId;

    @Builder
    public ProVO(Long proId, String proName, String phoneNumber, String proImg, Long proStarRate, Long empCnt, Long locationId,
                 String userId){
        this.proId = proId;
        this.proName = proName;
        this.phoneNumber = phoneNumber;
        this.proImg = proImg;
        this.proStarRate = proStarRate;
        this.empCnt = empCnt;
        this.locationId = locationId;
        this.userId = userId;
    }

    public static ProVO toEntity(ProDTO ProDTO){
        return ProVO.builder().proId(ProDTO.getProId())
                .proName(ProDTO.getProName())
                .phoneNumber(ProDTO.getPhoneNumber())
                .proImg(ProDTO.getProImg())
                .proStarRate(ProDTO.getProStarRate())
                .empCnt(ProDTO.getEmpCnt())
                .locationId(ProDTO.getLocationId())
                .userId(ProDTO.getUserId())
                .build();

    }
}
