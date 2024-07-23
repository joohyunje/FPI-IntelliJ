package com.example.fpi.controller.pro;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CardInfoFileDTO;
import com.example.fpi.domain.dto.pro.ProDTO;
import com.example.fpi.domain.dto.pro.ProEditDTO;
import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.pro.ProMapper;
import com.example.fpi.service.main.FormService;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pro")
@RequiredArgsConstructor
public class ProMypageController {
    private final ProService proService;
    private final UserService userService;
    private final FormService formService;


    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model, HttpSession session) {
        String userId= customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);

        ProDTO pro = proService.detailPro(proId);

        session.removeAttribute("loginName");

        session.setAttribute("proName", pro.getProName());
        model.addAttribute("mypage", pro);

        return "pro/mypage/pro_mypage";

    }


    @GetMapping("/detail")
    public String detail(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model){
        Long proId = proService.selectProId(customOAuth2User.getUserId());
        List<CardInfoFileDTO> files = proService.selectCardInfoFile(proId);
        List<CardInfoDTO> cards = proService.selectCard(proId);

        model.addAttribute("detail",proService.detailPro(proId));
        model.addAttribute("files", files);
        model.addAttribute("cards", cards);
        System.out.println(cards);
        return "pro/mypage/pro_detail";

    }
    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {
        Long proId = proService.selectProId(customOAuth2User.getUserId());
        List<CardInfoFileDTO> files = proService.selectCardInfoFile(proId);
        List<CardInfoDTO> cards = proService.selectCard(proId);

        model.addAttribute("edit", proService.selectEditPro(proId));
        model.addAttribute("files", files);
        model.addAttribute("cards", cards);
        System.out.println(proService.selectEditPro(proId));
        return "pro/mypage/pro_edit";
    }

    @PostMapping("/edit")
    public String edit(ProEditDTO edit, @RequestParam List<MultipartFile> files,
                       @RequestParam MultipartFile proProfile) throws IOException {

        String region = edit.getRegion();
        String city = edit.getCity();
        edit.setLocationId(formService.selectLocation(region,city));
        System.out.println(edit);
        proService.updatePro(edit,files,proProfile);

        return "redirect:/pro/detail";

    }


//    전문가 회원탈퇴
    @PostMapping("/delete")
    public String delete(@RequestParam Long proId,
                         @RequestParam String proName, //폼에서 적은 프로이름
                         RedirectAttributes redirectAttributes,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                         HttpSession session) throws IOException {
        String dbProName = proService.getProName(proId);
        String userId= customOAuth2User.getUserId();
        Long cardInfoId = proService.selectEditPro(proId).getCardInfoId();


//        db에 저장된 이름이랑 받아온 input에 입력된 이름이랑 비교
        if(proName.equals(dbProName)){
            proService.deleteCardFile(cardInfoId);
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
