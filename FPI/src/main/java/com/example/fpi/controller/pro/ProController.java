package com.example.fpi.controller.pro;

import com.example.fpi.domain.dto.pro.ProCareerInfoListDTO;
import com.example.fpi.domain.dto.pro.ProRequestDetailDTO;
import com.example.fpi.domain.dto.pro.ProUploadDTO;
import com.example.fpi.domain.dto.user.UserRequestDetailDTO;
import com.example.fpi.domain.dto.user.UserUploadDetailDTO;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pro")
@RequiredArgsConstructor
public class ProController {

    private final ProService proService;
    private final UserService userService;

    @GetMapping("/requests")
    public String rest() {
        return "pro/req_list/pro_received_req";
    }

    //    전문가가 보낸 요청의 상세보기
    @GetMapping("/proDetail/{proRequestId}")
    public String proReqDetail(@PathVariable("proRequestId") Long proRequestId, Model model) {

        ProRequestDetailDTO proRequest = proService.selectProReqDetail(proRequestId);
        List<ProCareerInfoListDTO> careerInfo = proService.selectProCareerByReq(proRequestId);

        model.addAttribute("proRequest", proRequest);
        model.addAttribute("careerInfo", careerInfo);

        return "/pro/req_list/pro_send_req_info";

    }

    //    유저가 보낸 요청의 상세보기
    @GetMapping("/userDetail/{userRequestId}")
    public String userReqDetail(@PathVariable("userRequestId") Long userRequestId, Model model) {

        UserRequestDetailDTO userRequest = userService.selectUserReqDetail(userRequestId);

        model.addAttribute("userRequest", userRequest);

        return "/pro/req_list/pro_received_req_info";

    }

//    회원 찾기
    @GetMapping("/userFind")
    public String uploadRest() {
        return "pro/userfind/FindUser";
    }

//    회원 찾기를 통해 유저가 올리 견적 상세보기
    @GetMapping("/uploadDetail/{userUploadId}")
    public String uploadDetail(@PathVariable("userUploadId") Long userUploadId, Model model) {
        UserUploadDetailDTO Upload = userService.selectUserUploadDetail(userUploadId);

        model.addAttribute("userUpload", Upload);

        return "/pro/userfind/userFind_info";
    }

//    전문가 견적 작성하기
    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("proUpload", new ProUploadDTO());

        return "/pro/upload/pro_receiveform";
    }

    @PostMapping("/upload")
    public String upload(ProUploadDTO proUpload, @RequestParam("proId") Long proId) {

        proUpload.setProId(proId);


        return "redirect:/main";
    }
}
