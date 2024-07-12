package com.example.fpi.service.user;

import org.springframework.stereotype.Service;

@Service

public interface CouponService {
    int couponCount(String userId);
}
