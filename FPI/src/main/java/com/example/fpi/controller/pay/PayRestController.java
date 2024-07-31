package com.example.fpi.controller.pay;

import com.example.fpi.domain.dto.pay.CashDTO;
import com.example.fpi.service.user.PayCouponService;
import com.example.fpi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/rest")
@RequiredArgsConstructor
public class PayRestController {

    private final UserService userService;
    private final PayCouponService payCouponService;

    @PostMapping("/cash")
    public String point(@RequestBody CashDTO cash) {

        System.out.println(cash);
        payCouponService.updateCash(cash.getUserId(),cash.getUserPlusCash());
        payCouponService.useCoupon(cash.getCouponId());



        return "/user/mypage";
    }

}
