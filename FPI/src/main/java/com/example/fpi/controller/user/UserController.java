package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.pro.ProCareerInfoListDTO;
import com.example.fpi.domain.dto.pro.ProRequestDetailDTO;
import com.example.fpi.domain.dto.pro.ProUploadDetailDTO;
import com.example.fpi.domain.dto.user.UserRequestDetailDTO;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProService proService;

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
    public String uploadDetail(@PathVariable("proUploadId") Long proUploadId, Model model) {
        ProUploadDetailDTO Upload = proService.selectProUploadDetail(proUploadId);

        List<ProCareerInfoListDTO> careerInfo = proService.selectProCareerByUp(proUploadId);


        model.addAttribute("careerInfo", careerInfo);

        model.addAttribute("proUpload", Upload);

        return "/user/profind/proFind_info";
    }

}
