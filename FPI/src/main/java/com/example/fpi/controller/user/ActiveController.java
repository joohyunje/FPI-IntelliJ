package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.service.user.ActiveService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/activeList")
@RequiredArgsConstructor
public class ActiveController {

//    마이페이지 활동내역 게시글
    private final ActiveService activeService;
    @GetMapping("/community")
    public String activeListcommunity(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                         HttpSession session,
                         @RequestParam(value="page", defaultValue = "1")int page,
                         @RequestParam (value="pageSize", defaultValue = "8")int pageSize,
                         Model model) {
        String userId = customOAuth2User.getUserId();
        String loginName = (String) session.getAttribute("loginName");
        String proName = (String) session.getAttribute("proName");
        String myName = (loginName != null) ? loginName : proName;

//        게시글 페이징 처리 부분
        int totalCommu = activeService.countUserCommu(userId,myName); //전체 게시글
        int totalPages =(int) Math.ceil((double)totalCommu/pageSize); //페이지 수
        List<UserCommunityListDTO> lists = activeService.selectUserCommuList(userId,myName,page,pageSize);
        int pageGroupSize=5; //페이지 그룹
        int startPage=((page-1)/pageGroupSize)* pageGroupSize +1; //그룹의 시작페이지 구함
        int endPage= Math.min(startPage+pageGroupSize -1,totalPages); //

        model.addAttribute("lists",lists);
        model.addAttribute("currentPage",page);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);


        return "/user/activeList/community";
    }
    //    마이페이지 활동내역 댓글

    @GetMapping("/comment")
    public String activeListComment(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                         HttpSession session,
                         @RequestParam(value="page", defaultValue = "1")int page,
                         @RequestParam (value="pageSize", defaultValue = "8")int pageSize,
                         Model model) {
        String userId = customOAuth2User.getUserId();
        String loginName = (String) session.getAttribute("loginName");
        String proName = (String) session.getAttribute("proName");
        String myName = (loginName != null) ? loginName : proName;

//        게시글 페이징 처리 부분
        int totalCommu = activeService.countUserComment(userId,myName); //전체 게시글
        int totalPages =(int) Math.ceil((double)totalCommu/pageSize); //페이지 수
        List<CommentListDTO> lists = activeService.selectUserCommentList(userId,myName,page,pageSize);
        int pageGroupSize=5; //페이지 그룹
        int startPage=((page-1)/pageGroupSize)* pageGroupSize +1; //그룹의 시작페이지 구함
        int endPage= Math.min(startPage+pageGroupSize -1,totalPages); //

        model.addAttribute("lists",lists);
        model.addAttribute("currentPage",page);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);


        return "/user/activeList/comment";
    }
}
