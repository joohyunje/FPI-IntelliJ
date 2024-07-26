package com.example.fpi.controller.pay;

import com.example.fpi.domain.dto.pay.CashDTO;
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

    @PostMapping("/cash")
    public String point(@RequestBody CashDTO cash) {

        int userCash=userService.detailUser(cash.getUserId()).getUserCash();

        userService.updateCash(cash.getUserId(),userCash,cash.getUserPlusCash());



        return "/user/mypage";
    }

}
