package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class CouponDTO {
    private int couponId;
    private String couponNumber;
    private LocalDateTime couponDate;
    private String state;
    private int discount;
    private String userId;
    private int count;
}
