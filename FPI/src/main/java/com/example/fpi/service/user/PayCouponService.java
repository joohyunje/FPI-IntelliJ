package com.example.fpi.service.user;

import com.example.fpi.domain.dto.user.CouponDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface PayCouponService {
//    쿠폰 유무에 따라 화면 달라짐
    int useCouponCount(String userId);
//    쿠폰 지급
    void userCoupon(String userId);

//    쿠폰리스트 보여줌
    List<CouponDTO> couponlist(String userId);

//  캐시 충전
    void updateCash(String userId, int userPlusCash);

    void useCoupon(Long couponId);

    // 유저가 올린글,전문가가 서비스 요청의 결제
    void proRequsestPay(Long proRequestId,String userId,Long proId);

//    전문가가 올린글,유저가 서비스 요청의 결제
    void userRequsestPay(Long userRequestId,String userId,Long proId);

}
