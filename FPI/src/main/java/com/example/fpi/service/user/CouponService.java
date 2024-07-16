package com.example.fpi.service.user;

import com.example.fpi.domain.dto.user.CouponDTO;
import com.example.fpi.domain.dto.user.CouponListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface CouponService {
//    쿠폰 유무에 따라 화면 달라짐
    int useCouponCount(String userId);
//    쿠폰 지급
    void userCoupon(String userId);

//    쿠폰리스트 보여줌
    List<CouponDTO> couponlist(String userId);


}
