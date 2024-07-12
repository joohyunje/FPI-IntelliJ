package com.example.fpi.controller.pro;

import com.example.fpi.domain.dto.pro.ProDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.mapper.pro.ProMapper;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.UserService;
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
    public String mypage(String userId, Model model) {

        model.addAttribute("mypage",proService.detailPro(userId));
        return "pro/mypage/pro_mypage";

    }



//    @GetMapping ("/{proId}")
//    public String index(@PathVariable("proId") Long proId, Model model){
//        model.addAttribute("mypage",proService.detailPro(proId));
//        return "pro/mypage/pro_mypage";
//    }
//    @GetMapping("/detail/{proId}")
//    public String detail(@PathVariable("proId") Long proId, Model model){
//        model.addAttribute("detail",proService.detailPro(proId));
//        return "pro/mypage/pro_detail";
//
//    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long proId,
                         @RequestParam String proName,
                         RedirectAttributes redirectAttributes){
        String dbProName = proDTO.getProName();;
//        db에 저장된 이름이랑 받아온 input에 입력된 이름이랑 비교
        if(proName.equals(dbProName)){
            proService.deletePro(proId,proName);
            return "redirect:/main";
        }
        else {
//            일치하지않을때 에러모달 띄우기 위한 코드
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/pro/mypage/detail/"+proId;
        }

    }


}
