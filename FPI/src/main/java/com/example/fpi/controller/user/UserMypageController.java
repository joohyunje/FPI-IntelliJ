package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserMypageController {
    private final UserService userService;
    private final UserDTO userDTO;
    private final UserVO userVO;

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
            session.invalidate();
            return "redirect:/main";
        } else {
            //            일치하지않을때 에러모달 띄우기 위한 코드
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/user/detail";
        }
    }

}
