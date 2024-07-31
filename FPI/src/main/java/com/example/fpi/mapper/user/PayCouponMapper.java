package com.example.fpi.mapper.user;

import com.example.fpi.domain.dto.user.CouponDTO;
import com.example.fpi.domain.vo.user.CouponVO;
import com.example.fpi.domain.vo.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayCouponMapper {
//    쿠폰유무에 따라 화면 달라짐
    int couponCount(String userId);
//    쿠폰 생성,회원가입시 지급
    void makeCoupon(CouponVO couponVO);

//    화면에 뿌려줄 쿠폰정보 가져옴
    List<CouponDTO> couponInfo(String userId);

//    캐시충전
    void updateCash(String userId,int userPlusCash);
//    쿠폰사용
    void useCoupon(Long couponId);

//    유저가 올린글,전문가가 요청
    Long cashRequest(Long proRequestId); //전문가에게 보낼금액
    void UserMinusCash(String userId,Long proRequestPay); //캐쉬차감
    void ProPlusCash(String userId,Long proRequestPay); //캐쉬받음

//    전문가가 올린글,유저가 견적 요청
    Long userCashRequest(Long userRequestId); //전문가에게 보낼금액
    void UserRequestMinusCash(String userId,Long userRequestPay); //캐쉬차감
    void UserRequestProPlusCash(String userId,Long userRequestPay); //캐쉬받음

}
