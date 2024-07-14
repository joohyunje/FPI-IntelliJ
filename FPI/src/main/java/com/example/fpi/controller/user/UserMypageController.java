package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.user.CouponDTO;
import com.example.fpi.domain.dto.user.CouponListDTO;
import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.user.CouponMapper;
import com.example.fpi.service.user.CouponService;
import com.example.fpi.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserMypageController {
    private final UserService userService;
    private final UserDTO userDTO;
    private final UserVO userVO;
    private final CouponService couponService;
    private final CouponMapper couponMapper;

    //    마이페이지
    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {
//        현재사용자의 인증객체 가져옴
        String userId = customOAuth2User.getUserId();
        model.addAttribute("mypage",userService.detailUser(userId));
        return "user/mypage/user_mypage";

    }
// 상세페이지
    @GetMapping("/detail")
    public String detail(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {

        String userId = customOAuth2User.getUserId();
        model.addAttribute("detail", userService.detailUser(userId));

        return "user/mypage/user_detail";

    }

    @GetMapping("/couponlist")
    public String coupon(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,Model model){
        String userId = customOAuth2User.getUserId();
        System.out.println();

        List<CouponDTO> coupons = couponService.couponlist(userId);

        for(CouponDTO coupon : coupons){
            coupon.setCount(couponService.useCouponCount(userId));
        }


        model.addAttribute("coupons",coupons);
        System.out.println(coupons);

        return "user/mypage/coupon";
    }

    //    유저정보 수정 폼으로 이동
    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {
        String userId = customOAuth2User.getUserId();
        model.addAttribute("edit", userService.detailUser(userId));

        return "user/mypage/user_edit";
    }


    //유저 탈퇴

    @PostMapping("/delete")
    public String delete(@RequestParam String userId,
                         @RequestParam String userName,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {

        String dbUserName = userService.getUserName(userId);

        //        db에 저장된 이름이랑 받아온 input에 입력된 이름이랑 비교
        if (userName.equals(dbUserName)) {
            userService.deleteUser(userId,userName);
//            헤더에서 이걸 이용해서 이름을 읽어들이기때문에
//            탈퇴후 헤더에서도 이름을 삭제해주기 위해 session을 비워줘야함
            session.invalidate();
            return "redirect:/main";
        } else {
            //            일치하지않을때 에러모달 띄우기 위한 코드
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/user/detail";
        }
    }

}
