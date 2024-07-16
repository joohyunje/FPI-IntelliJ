package com.example.fpi.domain.vo.kakaopay;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class kakaoPayReadyVO {
    private String tid; //결제 고유번호
    private String next_redirect_pc_url; //pc에서 카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면 Redirect URL
    private String partner_order_id; //가맹점주문번호

}

