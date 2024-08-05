package com.example.fpi.service.user;

import com.example.fpi.domain.dto.user.CouponDTO;
import com.example.fpi.domain.vo.user.CouponVO;
import com.example.fpi.mapper.pro.ProMapper;
import com.example.fpi.mapper.user.PayCouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PayCouponServiceImpl implements PayCouponService {
    private final PayCouponMapper payCouponMapper;
    private final ProMapper proMapper;

//    사용가능한 쿠폰갯수 리턴
    @Override
    public int useCouponCount(String userId) {
        return payCouponMapper.couponCount(userId);
    }

//    쿠폰지급 관련
    @Override
    public void userCoupon(String userId) {
        CouponDTO couponDTO = new CouponDTO();

//        쿠폰번호 만들기 위해 필요
        int n = 12; // n자리 쿠폰
        char[] chs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        Random rd = new Random(); //난수생성


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
            couponDTO.setCouponNum(sb.toString()); //쿠폰번호가 저장
            couponDTO.setUserId(userId);

//            값을 랜덤하게 가져옴
            int rdNum=(int)(Math.random()* num.length);
            if(i==0){ //처음에는 무조건 10000원 지급
                couponDTO.setDiscount(10000);
            }
            else{ //그 다음엔 금액 랜덤 지금
                couponDTO.setDiscount(num[rdNum]);
            }


            payCouponMapper.makeCoupon(CouponVO.toEntity(couponDTO));
        }

    }

//    쿠폰 정보 가져옴
    @Override
    public List<CouponDTO> couponlist(String userId) {
        return payCouponMapper.couponInfo(userId);
    }

//    포인트 충전기능
    @Override
    public void updateCash(String userId, int userPlusCash) {
        payCouponMapper.updateCash(userId, userPlusCash);
    }

    @Override
    public void useCoupon(Long couponId) {
        payCouponMapper.useCoupon(couponId);
    }


    //    회원이 받은 요청에서 작업완료 누르면 전문가에게 보내졌다는 문구
    @Override
    public void proRequsestPay(Long proRequestPay, String userId,Long proId) {
        payCouponMapper.UserMinusCash(userId,proRequestPay); //회원의 포인트 빠져나감

        String payUserID = proMapper.selectProInfo(proId).getUserId(); //전문가 아이디로 그사람의 유저아이디 찾기
        payCouponMapper.ProPlusCash(payUserID,proRequestPay); //전문가포인트 추가
    }

//    전문가가 받은 요청에서 작업완료 누르면 회원에게서 포인트빠져나감 문구
    @Override
    public void userRequsestPay(Long userRequestPay, String userId, Long proId) {
        payCouponMapper.UserRequestMinusCash(userId,userRequestPay); //회원의 포인트 빠져나감

        String payUserID = proMapper.selectProInfo(proId).getUserId(); //전문가 아이디로 그사람의 유저아이디 찾기
        payCouponMapper.UserRequestProPlusCash(payUserID,userRequestPay); //전문가포인트 추가
    }
}
