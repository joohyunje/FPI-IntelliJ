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

//    사용가능한 쿠폰갯수 리턴
    @Override
    public int useCouponCount(String userId) {
        return couponMapper.couponCount(userId);
    }

    @Override
    public void userCoupon(String userId) {
        CouponDTO couponDTO = new CouponDTO();

//        쿠폰번호 만들기 위해 필요
        int n = 12; // n자리 쿠폰
        char[] chs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        Random rd = new Random(); //난수생성

//        쿠폰갯수 1~3개 랜덤으로 저장
        int countCoupon = (int)(Math.random() * 3) + 1;

//        랜덤 지급할 금액들
        int [] num ={1000,3000,5000};

//        쿠폰갯수만큼 쿠폰테이블에 값을 insert
        for(int i=0;i<2;i++){

//            쿠폰갯수만큼 쿠폰번호를 랜덤으로 생성하는 for문
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                char ch = chs[rd.nextInt(chs.length)]; //nextInt() :해당범위안에서 난수 랜덤생성
                sb.append(ch);

            }
            couponDTO.setCouponNumber(sb.toString()); //쿠폰번호가 저장
            couponDTO.setUserId(userId);

//            값을 랜덤하게 가져옴
            int rdNum=(int)(Math.random()* num.length);
            if(i==0){ //처음에는 무조건 10000원 지금
                couponDTO.setDiscount(10000);
            }
            else{ //그 다음엔 금액 랜덤 지금
                couponDTO.setDiscount(num[rdNum]);
            }


            couponMapper.makeCoupon(CouponVO.toEntity(couponDTO));
        }

    }

//    쿠폰 정보 가져옴
    @Override
    public List<CouponDTO> couponlist(String userId) {
        return couponMapper.couponInfo(userId);
    }
}
