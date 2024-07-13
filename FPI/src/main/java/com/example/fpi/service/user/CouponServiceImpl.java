package com.example.fpi.service.user;

import com.example.fpi.domain.dto.user.CouponDTO;
import com.example.fpi.domain.dto.user.CouponListDTO;
import com.example.fpi.domain.vo.user.CouponVO;
import com.example.fpi.mapper.user.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponMapper couponMapper;
    @Override
    public int couponCount(String userId) {
        return couponMapper.couponCount(userId);
    }

    @Override
    public void userCoupon(String userId) {
        CouponDTO couponDTO = new CouponDTO();

//        쿠폰번호 만드는 랜덤함수
        int n = 12; // n자리 쿠폰
        char[] chs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = chs[rd.nextInt(chs.length)];
            sb.append(ch);
        }

//        쿠폰 금액 랜덤 지급
        int [] num ={1000,3000,5000};
        int rdNum=(int)(Math.random()* num.length);

        couponDTO.setCouponNumber(sb.toString());
        couponDTO.setUserId(userId);
        couponDTO.setDiscount(num[rdNum]);
        couponDTO.setUserId(userId);

        couponMapper.makeCoupon(CouponVO.toEntity(couponDTO));
    }

//    쿠폰 정보 가져옴
    @Override
    public List<CouponListDTO> couponInfo(String userId) {
        return couponMapper.couponInfo(userId);
    }
}
