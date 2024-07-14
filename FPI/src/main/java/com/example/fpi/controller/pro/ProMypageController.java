package com.example.fpi.controller.pro;

import com.example.fpi.domain.dto.pro.ProDTO;
import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.pro.ProMapper;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pro")
@RequiredArgsConstructor
public class ProMypageController {
    private final ProService proService;
    private final UserService userService;
    private final ProMapper proMapper;
    private final ProDTO proDTO;

    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {
        String userId= customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);

        model.addAttribute("mypage",proService.detailPro(proId));
        return "pro/mypage/pro_mypage";

    }


    @GetMapping("/detail")
    public String detail(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model){
        String userId= customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);
        model.addAttribute("detail",proService.detailPro(proId));
        return "pro/mypage/pro_detail";

    }

//    전문가 회원탈퇴
    @PostMapping("/delete")
    public String delete(@RequestParam Long proId,
                         @RequestParam String proName, //폼에서 적은 프로이름
                         RedirectAttributes redirectAttributes,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                         HttpSession session){
        String dbProName = proService.getProName(proId);
        String userId= customOAuth2User.getUserId();

//        db에 저장된 이름이랑 받아온 input에 입력된 이름이랑 비교
        if(proName.equals(dbProName)){
            proService.deletePro(proId,proName);
            userService.editApproval(userId);
//            유저의 헤더에 변화가 생기기때문에 세션을 비워줌
            session.invalidate();
            return "redirect:/main";
        }
        else {
//            일치하지않을때 에러모달 띄우기 위한 코드
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/pro/detail";
        }

    }


}
