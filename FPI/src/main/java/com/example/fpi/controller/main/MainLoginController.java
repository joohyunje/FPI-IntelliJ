package com.example.fpi.controller.main;

import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.user.CategoryDTO;
import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.main.FormMapper;
import com.example.fpi.mapper.user.UserMapper;
import com.example.fpi.service.main.FormService;
import com.example.fpi.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainLoginController {
    private final  UserMapper userMapper;
    private final FormService formService;
    private final UserService userService;


    @GetMapping()
    public String index() {

        return "main/main";
    }



    @GetMapping("/sign")
    public String sign(){
        return "main/OAuthform";
    }

    @PostMapping("/sign")
    public String sign(@RequestParam("userName") String userName, // form 에서 입력한 내 이름
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
        formService.insertCategoryList(category,customOAuth2User.getUserId());
        userMapper.updateUser(UserVO.toEntity(userDTO));

//        홈페이지가 바뀔때 헤더에서 계속 가지고 있어야 하기 때문에 세션에 담아줌
        session.setAttribute("loginName", userService.getUserName(userDTO.getUserId()));

        return "redirect:/main";

    }
    @GetMapping("login")
    public String goForm(HttpSession session){

        session.invalidate();

        return "main/login";
    }

}
