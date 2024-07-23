package com.example.fpi.controller.pro;

import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.dto.user.UserRequestDetailDTO;
import com.example.fpi.domain.dto.user.UserReviewDTO;
import com.example.fpi.domain.dto.user.UserUploadDetailDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String uploadDetail(@PathVariable("userUploadId") Long userUploadId,
                               @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                               Model model) {
        UserUploadDetailDTO Upload = userService.selectUserUploadDetail(userUploadId);

        String userId = customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);
        ProLocationDTO proLocation = proService.selectProLocation(proId);

        Long checkRequest = proService.checkProRequest(userUploadId, proId);

        model.addAttribute("userUpload", Upload);

        model.addAttribute("proLocation", proLocation);

        model.addAttribute("proRequest", new ProRequestDTO());

        model.addAttribute("checkRequest", checkRequest);

        return "/pro/userfind/userFind_info";
    }

    //    전문가 견적 작성하기
    @GetMapping("/upload")
    public String uploadForm(Model model,
                             @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String userId = customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);
        ProLocationDTO proLocation = proService.selectProLocation(proId);

        model.addAttribute("proUpload", new ProUploadDTO());
        model.addAttribute("proLocation", proLocation);

        return "/pro/upload/pro_receiveform";
    }

    @PostMapping("/upload")
    public String upload(ProUploadDTO proUpload,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                         @RequestParam("proUploadFiles") List<MultipartFile> files) {

        if (customOAuth2User != null) {
            String userId = customOAuth2User.getUserId();
            Long proId = proService.selectProId(userId);
            proUpload.setProId(proId);
            System.out.println("proId " + proId);
        }

        System.out.println("proUpload " + proUpload.toString());

        proService.saveProUpload(proUpload, files);

        return "redirect:/main/pro";
    }

    @PostMapping("/sendProRequest")
    public String sendRequest(@RequestParam Long userUploadId,
                              ProRequestDTO proRequest,
                              @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String userId = customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);
        proRequest.setProId(proId);
        proRequest.setUserUploadId(userUploadId);


        proService.proRequest(proRequest);


        return "redirect:/pro/uploadDetail/" + userUploadId;

    }

    @GetMapping("/userReview")
    public String userReviewForm(Model model) {

        model.addAttribute("userReview", new UserReviewDTO());

        return "/pro/req_list/reviewWrite";
    }

    @PostMapping("/userReview")
    public String userReview(UserReviewDTO userReview,
                             @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                             @RequestParam String userId) {

        String user = customOAuth2User.getUserId();
        Long proId = proService.selectProId(user);
        userReview.setProId(proId);
        userReview.setUserId(userId);

        proService.proWriteUserReview(userReview);


        return "redirect:/pro/main";

    }

    @PostMapping("/userDetail/delete/{userRequestId}")
    public String deleteUserRequest(@PathVariable Long userRequestId) {
        proService.deleteUserRequest(userRequestId);

        return "redirect:/pro/requests";
    }

}
