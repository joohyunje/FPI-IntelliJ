package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.file.ProCardInfoFileDTO;
import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.dto.user.*;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.mapper.File.FileMapper;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.PayCouponService;
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
    private final PayCouponService payCouponService;

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
    public String proReqDetail(@PathVariable("proRequestId") Long proRequestId, Model model,@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        ProRequestDetailDTO proRequest = proService.selectProReqDetail(proRequestId);

        List<ProCareerInfoListDTO> careerInfo = proService.selectProCareerByReq(proRequestId);

        Long proId = userService.selectProIdByProRequestId(proRequestId);

        List<ProCardInfoFileDTO> proCardInfoFiles = fileMapper.selectProCardFileList(proId);

        //        보유캐시보다 수락할 금액이 작으면 작업완료 불가능
        String userId=customOAuth2User.getUserId();
        Long userCash = userId.equals("0")? 0L :userService.detailUser(userId).getUserCash();

        System.out.println(proRequestId);
        System.out.println(careerInfo);

        model.addAttribute("proCardInfoFiles", proCardInfoFiles);
        model.addAttribute("proAccuse", new ProAccuseDTO());
        model.addAttribute("proRequest", proRequest);
        model.addAttribute("careerInfo", careerInfo);
        model.addAttribute("userCash", userCash);

        return "/user/req_list/user_received_req_info";

    }


    //    유저가 보낸 요청의 상세보기
    @GetMapping("/userDetail/{userRequestId}")
    public String userReqDetail(@PathVariable("userRequestId") Long userRequestId,
                                Model model) {

        UserRequestDetailDTO userRequest = userService.selectUserReqDetail(userRequestId);
        Long proId = userService.selectProIdByUserRequestId(userRequestId);
        System.out.println(userRequest.getCheckUserReview());

        System.out.println(userRequest.toString());

        model.addAttribute("userRequest", userRequest);
        model.addAttribute("proId", proId);

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
//        비로그인 상태면 userId에 null을 주고, 클릭시 우측 견적요청 부분이 안보이게 함
        String userId = customOAuth2User == null? "0":customOAuth2User.getUserId();
        Long userCash = userId.equals("0")? 0L :userService.detailUser(userId).getUserCash();



        Long proId = proService.selectProIdByProUploadId(proUploadId);
        UserLocationDTO userLocation = userService.selectUserLocation(userId);

        ProUploadDetailDTO Upload = proService.selectProUploadDetail(proUploadId);

        List<ProCareerInfoListDTO> careerInfo = proService.selectProCareerByUp(proUploadId);

        List<ProUploadFileDTO> proUploadFiles = fileMapper.selectProUploadFileList(proUploadId);

        List<ProCardInfoFileDTO> proCardInfoFiles = fileMapper.selectProCardFileList(proId);


        Long checkRequest = userService.checkUserRequest(proUploadId, userId);

        model.addAttribute("careerInfo", careerInfo);

        model.addAttribute("proUpload", Upload);

        model.addAttribute("proUploadFiles", proUploadFiles);

        model.addAttribute("proCardInfoFiles", proCardInfoFiles);

        model.addAttribute("userLocation", userLocation);

        model.addAttribute("userRequest", new UserRequestDTO());

        model.addAttribute("checkRequest", checkRequest);
        model.addAttribute("loginOk",userId);
        model.addAttribute("userCash", userCash);


        return "/user/profind/proFind_info";
    }

    //    회원 견적 작성하기
    @GetMapping("/upload")
    public String uploadForm(Model model,
                             @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        String userId = customOAuth2User.getUserId();
        UserLocationDTO userLocation = userService.selectUserLocation(userId);
        UserDTO userDetail = userService.detailUser(userId);

        model.addAttribute("userDetail", userDetail);
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

    @GetMapping("/proReview/{proRequestId}")
    public String proReviewForm(@PathVariable Long proRequestId,
                                Model model) {

        Long proId = userService.selectProIdByProRequestId(proRequestId);

        model.addAttribute("proRequestId", proRequestId);
        model.addAttribute("proReview", new ProReviewDTO());
        model.addAttribute("proId", proId);
        model.addAttribute("proName", proService.getProName(proId));

        return "/user/req_list/reviewWrite";
    }

    @GetMapping("/proReview2/{userRequestId}")
    public String proReviewForm2(@PathVariable Long userRequestId,
                                 Model model) {

        Long proId = userService.selectProIdByUserRequestId(userRequestId);

        model.addAttribute("userRequestId", userRequestId);
        model.addAttribute("proReview", new ProReviewDTO());
        model.addAttribute("proId", proId);
        model.addAttribute("proName", proService.getProName(proId));

        return "/user/req_list/reviewWrite";
    }

    @PostMapping("/proReview")
    public String proReview(ProReviewDTO proReview,
                            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                            @RequestParam Long proId,
                            @RequestParam Long proRequestId,
                            @RequestParam Long userRequestId) {

        String userId = customOAuth2User.getUserId();
//        String userName = userService.getUserName(userId);
        proReview.setUserId(userId);
        proReview.setProId(proId);

        System.out.println(proReview.toString());

        System.out.println(proRequestId);

        if (proRequestId == 0) {
            userService.updateUserRequestProReview(userRequestId);
        } else {
            userService.updateProRequestProReview(proRequestId);
        }


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
//회원이 올린 글에 전문가가 견적요청 보냈음,회원이 수락,작업완료
    @PostMapping("/proDetail/updateComplete/{proRequestId}")
    public String updateComplete(@PathVariable Long proRequestId,@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        Long proId = userService.selectProIdByProRequestId(proRequestId); //전문가요청아이디로 전문가 아이디 찾기
        Long empCnt = proService.empCount(proId);
        String userId = customOAuth2User.getUserId();
        proService.updateEmpCnt(proId, empCnt);

        System.out.println(proId);
        System.out.println(empCnt);

        userService.updateUserComplete(proRequestId);
        payCouponService.proRequsestPay(proRequestId,userId,proId); //캐쉬결제하는서비스

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
