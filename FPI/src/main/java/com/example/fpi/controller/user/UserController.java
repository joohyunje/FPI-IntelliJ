package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.dto.user.UserLocationDTO;
import com.example.fpi.domain.dto.user.UserRequestDTO;
import com.example.fpi.domain.dto.user.UserRequestDetailDTO;
import com.example.fpi.domain.dto.user.UserUploadDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.mapper.File.FileMapper;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProService proService;
    private final FileMapper fileMapper;

//    @GetMapping("/received_req")
//    public String received_req(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
//                               @RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
//                               Model model) {
//        int totalRequests = userService.getRequestCount();
//        int totalPages = (int) Math.ceil((double)totalRequests/pageSize);
//
//        List<UserReceivedReqListDTO> reqItems = userService.getUserReceivedReq(pageNo, pageSize);
//
//        int pageGroupSize = 5;
//        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
//        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);
//
//
//        model.addAttribute("reqItems", reqItems);
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("totalPages", totalPages);
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        return "user/req_list/user_received_req";
//    }

    @GetMapping("/requests")
    public String rest() {
        return "user/req_list/user_received_req";
    }

    //    전문가가 보낸 요청의 상세보기
    @GetMapping("/proDetail/{proRequestId}")
    public String proReqDetail(@PathVariable("proRequestId") Long proRequestId, Model model) {

        ProRequestDetailDTO proRequest = proService.selectProReqDetail(proRequestId);

        List<ProCareerInfoListDTO> careerInfo = proService.selectProCareerByReq(proRequestId);

        System.out.println(proRequestId);
        System.out.println(careerInfo);


        model.addAttribute("proAccuse", new ProAccuseDTO());
        model.addAttribute("proRequest", proRequest);
        model.addAttribute("careerInfo", careerInfo);

        return "/user/req_list/user_received_req_info";

    }

    //    유저가 보낸 요청의 상세보기
    @GetMapping("/userDetail/{userRequestId}")
    public String userReqDetail(@PathVariable("userRequestId") Long userRequestId, Model model) {

        UserRequestDetailDTO userRequest = userService.selectUserReqDetail(userRequestId);

        model.addAttribute("userRequest", userRequest);

        return "/user/req_list/user_send_req_info";

    }

    @GetMapping("/proFind")
    public String uploadRest() {
        return "user/profind/FindPro";
    }

    //    전문가 찾기를 통해 전문가가 올리 견적 상세보기
    @GetMapping("/uploadDetail/{proUploadId}")
    public String uploadDetail(@PathVariable("proUploadId") Long proUploadId, Model model,
                               @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        ProUploadDetailDTO Upload = proService.selectProUploadDetail(proUploadId);

        List<ProCareerInfoListDTO> careerInfo = proService.selectProCareerByUp(proUploadId);

        List<ProUploadFileDTO> proUploadFiles = fileMapper.selectProUploadFileList(proUploadId);

        String userId = customOAuth2User.getUserId();
        UserLocationDTO userLocation = userService.selectUserLocation(userId);

        Long checkRequest = userService.checkUserRequest(proUploadId, userId);

        model.addAttribute("careerInfo", careerInfo);

        model.addAttribute("proUpload", Upload);

        model.addAttribute("proUploadFiles", proUploadFiles);

        model.addAttribute("userLocation", userLocation);

        model.addAttribute("userRequest", new UserRequestDTO());

        model.addAttribute("checkRequest", checkRequest);

        return "/user/profind/proFind_info";
    }

    //    회원 견적 작성하기
    @GetMapping("/upload")
    public String uploadForm(Model model,
                             @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        String userId = customOAuth2User.getUserId();
        UserLocationDTO userLocation = userService.selectUserLocation(userId);

        model.addAttribute("userUpload", new UserUploadDTO());
        model.addAttribute("userLocation", userLocation);

        return "/user/upload/user_receiveform";
    }

    @PostMapping("/upload")
    public String upload(UserUploadDTO userUpload,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                         @RequestParam("userUploadFiles") List<MultipartFile> files) {

        if (customOAuth2User != null) {
            String userId = customOAuth2User.getUserId();
            userUpload.setUserId(userId);
            System.out.println("userId " + userId);
        }

        System.out.println("userUpload " + userUpload.toString());


        userService.saveUserUpload(userUpload, files);

        return "redirect:/main/user";
    }

    @PostMapping("/sendUserRequest")
    public String sendRequest(@RequestParam Long proUploadId,
                              UserRequestDTO userRequest,
                              @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String userId = customOAuth2User.getUserId();
        userRequest.setUserId(userId);
        userRequest.setProUploadId(proUploadId);


        userService.userRequest(userRequest);


        return "redirect:/user/uploadDetail/" + proUploadId;

    }

    @GetMapping("/proReview/{proId}")
    public String proReviewForm(@PathVariable Long proId,
                                Model model) {

        model.addAttribute("proReview", new ProReviewDTO());
        model.addAttribute("proId", proId);
        model.addAttribute("proName", proService.getProName(proId));

        return "/user/req_list/reviewWrite";
    }

    @PostMapping("/proReview")
    public String proReview(ProReviewDTO proReview,
                            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                            @RequestParam Long proId) {

        String userId = customOAuth2User.getUserId();
//        String userName = userService.getUserName(userId);
        proReview.setUserId(userId);
        proReview.setProId(proId);

        System.out.println(proReview.toString());


        userService.userWriteProReview(proReview);

        return "redirect:/main/user";

    }

    @PostMapping("/proDetail/delete/{proRequestId}")
    public String deleteProRequest(@PathVariable Long proRequestId) {
        userService.deleteProRequest(proRequestId);

        return "redirect:/user/requests";
    }

    @PostMapping("/proDetail/updateAccept/{proRequestId}")
    public String updateAccept(@PathVariable Long proRequestId) {

        userService.updateUserAccept(proRequestId);
        return "redirect:/user/proDetail/" + proRequestId;
    }

    @PostMapping("/proDetail/updateComplete/{proRequestId}")
    public String updateComplete(@PathVariable Long proRequestId) {

        userService.updateUserComplete(proRequestId);
        return "redirect:/user/proDetail/" + proRequestId;
    }

    @PostMapping("/accusePro")
    public String accusePro(@RequestParam Long proRequestId,
                            ProAccuseDTO proAccuse,
                            @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        String userId = customOAuth2User.getUserId();
        Long proId = userService.selectProIdByProRequestId(proRequestId);

        proAccuse.setUserId(userId);
        proAccuse.setProId(proId);

        userService.userAccusePro(proAccuse);
        userService.deleteProRequest(proRequestId);

        return "redirect:/user/requests";
    }


}
