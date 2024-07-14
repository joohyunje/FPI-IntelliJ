package com.example.fpi.mapper.user;

import com.example.fpi.domain.dto.user.CouponDTO;
import com.example.fpi.domain.dto.user.CouponListDTO;
import com.example.fpi.domain.vo.user.CouponVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {
//    쿠폰유무에 따라 화면 달라짐
    int couponCount(String userId);
//    쿠폰 생성,회원가입시 지급
    void makeCoupon(CouponVO couponVO);

//    화면에 뿌려줄 쿠폰정보 가져옴
    List<CouponDTO> couponInfo(String userId);



}
