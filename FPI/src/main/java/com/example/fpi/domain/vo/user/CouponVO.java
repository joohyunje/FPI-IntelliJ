package com.example.fpi.domain.vo.user;
import com.example.fpi.domain.dto.user.CouponDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@NoArgsConstructor
public class CouponVO {
    private int couponId;
    private String couponNumber;
    private LocalDateTime couponDate;
    private String state;
    private int discount;
    private String userId;

    @Builder
    public CouponVO(int couponId, String couponNumber, LocalDateTime couponDate, String state, int discount,String userId) {
        this.couponId = couponId;
        this.couponNumber = couponNumber;
        this.couponDate = couponDate;
        this.state = state;
        this.discount = discount;
        this.userId = userId;

    }
    public static CouponVO toEntity(CouponDTO dto){
        return CouponVO.builder()
                .couponId(dto.getCouponId())
                .couponNumber(dto.getCouponNum())
                .couponDate(dto.getCouponValDate())
                .state(dto.getState())
                .discount(dto.getDiscount())
                .userId(dto.getUserId())
                .build();


    }


}
