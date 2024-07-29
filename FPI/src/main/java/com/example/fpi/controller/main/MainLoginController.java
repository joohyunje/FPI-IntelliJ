package com.example.fpi.controller.main;


import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.vo.user.UserVO;

import com.example.fpi.mapper.main.MainListMapper;
import com.example.fpi.mapper.pro.ProMapper;
import com.example.fpi.mapper.user.UserMapper;
import com.example.fpi.service.board.CommunityService;
import com.example.fpi.service.main.FormService;
import com.example.fpi.service.main.MainListService;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.CouponService;
import com.example.fpi.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainLoginController {
    private final  UserMapper userMapper;
    private final FormService formService;
    private final UserService userService;
    private final CouponService couponService;
    private final ProService proService;

    private final MainListService mainListService;

    //헤더에서 회원,전문가일때 구분 위해서 필요
    @GetMapping(value = {"/{status}", ""})
    public String index(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                        @PathVariable(required = false) String status,
                        Model model,
                        HttpSession session) {

        if(customOAuth2User != null){
            String userId = customOAuth2User.getUserId();
            if(status.equals("pro")){
                session.removeAttribute("loginName");
                session.setAttribute("proName", proService.getProName(proService.selectProId(userId)));
            }
            else if(status.equals("user")){
                session.removeAttribute("proName");
                session.setAttribute("loginName", userService.detailUser(userId).getUserName());
            }
        }
        model.addAttribute("communityList", mainListService.mainCommunityList());
        model.addAttribute("proUploadLists",mainListService.proUploadList());


        return "main/main";
    }




// 회원가입 폼으로 이동
    @GetMapping("/sign")
    public String sign(){
        return "main/OAuthform";
    }

//회원가입 폼
    @PostMapping("/sign")
    public String sign(@RequestParam("userName") String userName, // form에서 입력한 이름
                       @RequestParam("phoneNumber") String phoneNumber,
                       @RequestParam("email") String email,
                       @RequestParam("region") String region,
                       @RequestParam("city") String city,
                       @RequestParam("category") Long category,
                       @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                       HttpSession session){

        UserDTO userDTO = userMapper.findByUserId(customOAuth2User.getUserId());
        userDTO.setRole("basic");
        userDTO.setUserName(userName);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setEmail(email);
        userDTO.setRegion(region);
        userDTO.setCity(city);
        userDTO.setCategoryId(category);
        userDTO.setLocationId(formService.selectLocation(region,city));

//        OAuth 가입 필수 폼입력시 카테고리리스트 테이블에 정보 추가
        formService.insertCategoryList(category,customOAuth2User.getUserId());
//       입력한 정보를 회원테이블에 업데이트
        userMapper.updateUser(UserVO.toEntity(userDTO));
        couponService.userCoupon(userDTO.getUserId());
//        홈페이지가 바뀔때 헤더에서 계속 가지고 있어야 하기 때문에 회원가입 시 db에 담긴 이름을 세션에 담아줌
        session.setAttribute("loginName", userService.getUserName(userDTO.getUserId()));
//        전문가 전환 버튼에 필요
        session.setAttribute("userProApproval","NO");

        return "redirect:/main/user";

    }
    @GetMapping("login")
    public String goForm(HttpSession session){

        session.invalidate();

        return "main/login";
    }

}
