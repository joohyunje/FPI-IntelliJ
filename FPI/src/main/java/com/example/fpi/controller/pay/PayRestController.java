package com.example.fpi.controller.pay;

import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/rest")
@RequiredArgsConstructor
public class PayRestController {

    private final UserService userService;
    @PostMapping("/point")
    public String chargingOk(@RequestBody int result, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String userId = customOAuth2User.getUserId();
        userService.updateCash(userId,result);


        System.out.println(result);

        return "redirect:/user/mypage";
    }

}
