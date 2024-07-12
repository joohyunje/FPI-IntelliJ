package com.example.fpi.service.user;

import com.example.fpi.mapper.user.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponMapper couponMapper;
    @Override
    public int couponCount(String userId) {
        return couponMapper.couponCount(userId);
    }
}
