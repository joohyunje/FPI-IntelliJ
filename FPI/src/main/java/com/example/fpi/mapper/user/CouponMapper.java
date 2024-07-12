package com.example.fpi.mapper.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
    int couponCount(String userId);
}
