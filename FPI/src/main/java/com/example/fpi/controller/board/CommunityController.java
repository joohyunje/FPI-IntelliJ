package com.example.fpi.controller.board;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityDetailDTO;
import com.example.fpi.domain.dto.board.LikeDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.service.board.CommunityService;
import com.example.fpi.service.pro.ProService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final ProService proService;

    //페이징처리장소
    @GetMapping("/community")
    public String list() {
        return "community/community";
    }

    //    커뮤니티 게시판상세보기
    @GetMapping("/community/detail/{communityId}")
    public String detail(@PathVariable("communityId") Long communityId,Long commentId, Model model,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User,HttpSession session) {

        CommunityDetailDTO commu = communityService.getCommunityDetail(communityId,customOAuth2User,session);


//        로그인했다면, 그 로그인값을 detail에 넣어줌
        if(customOAuth2User != null && customOAuth2User.getUserId() != null){
            commu.setLoginUserId(customOAuth2User.getUserId());
        }

        model.addAttribute("commu", commu);
        model.addAttribute("commentId", commentId);

        return "/community/detail";
    }
//마이페이지에서 댓글누르면 해당 댓글위치로 이동
    @GetMapping("/community/detail/{communityId}/{commentId}")
    public String detailComment(@PathVariable("communityId") Long communityId,@PathVariable("commentId") Long commentId, Model model,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User,HttpSession session) {

        CommunityDetailDTO commu = communityService.getCommunityDetail(communityId,customOAuth2User,session);
//        로그인했다면, 그 로그인값을 detail에 넣어줌
        if(customOAuth2User != null && customOAuth2User.getUserId() != null){
            commu.setLoginUserId(customOAuth2User.getUserId());
        }

        model.addAttribute("commu", commu);
        model.addAttribute("commentId", commentId);
        System.out.println(commu.getLoginName());
        return "/community/detail";
    }

    //    게시글 작성시 가지고 이동할 정보
    @GetMapping("/community/write")
    public String writeForm(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User,HttpSession session){
        CommunityDetailDTO communityInfo = new CommunityDetailDTO();
        communityInfo.setUserId(customOAuth2User.getUserId());


//        해당값을 이용하여 카테고리 선택시 전문가팁 유무를 띄워줄것임
        if(session.getAttribute("loginName") == null){
            communityInfo.setProId(proService.selectProId(customOAuth2User.getUserId()));
        }
        else if(session.getAttribute("proName") == null){
            communityInfo.setProId(null);
        }
        model.addAttribute("communityInfo",communityInfo);

        return "/community/write";
    }
    //게시글 수정시 가지고 이동할 정보, 폼(postmapping) 지정
    @GetMapping("/community/edit/{communityId}")
    public String goEdit(@PathVariable Long communityId, Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, HttpSession session){
        CommunityDetailDTO communityInfo=communityService.getCommunityDetail(communityId,customOAuth2User,session);

//        전문가임
        if(session.getAttribute("loginName") == null){
            communityInfo.setProId(proService.selectProId(customOAuth2User.getUserId()));
            communityInfo.setLoginName((String) session.getAttribute("proName"));
        }
//        회원임
        else if(session.getAttribute("proName") == null){
            communityInfo.setProId(null);
            communityInfo.setLoginName( (String) session.getAttribute("loginName"));
        }

        communityInfo.setLoginUserId(customOAuth2User.getUserId());
        model.addAttribute("communityInfo",communityInfo);
        System.out.println(communityInfo);

        return "/community/write";
    }

    //    게시글 수정,작성,,수정과 작성 폼 주소 같음
    @PostMapping("/community/write")
    public String write(CommunityDTO communityInfo,HttpSession session){
        String loginName = (String) session.getAttribute("loginName");
        String proName = (String) session.getAttribute("proName");

//       게시글 아이디가 있고 작성자와 로그인된 사람이 같다면 =>수정
        if(communityInfo.getCommunityId() !=null && ( communityInfo.getAuthor() == loginName || communityInfo.getAuthor() == proName)){
            communityService.updateCommunity(communityInfo);

            return "redirect:/community/detail/" + communityInfo.getCommunityId();
        }


        if(session.getAttribute("loginName") == null){
            communityInfo.setAuthor(proName);
        }
        else if(session.getAttribute("proName") == null){
            communityInfo.setAuthor(loginName);
        }

//        커뮤니티 글작성
        communityService.saveCommunity(communityInfo);

        return "redirect:/community";
    }

    //    게시글 삭제
    @PostMapping("/community/delete/{communityId}")
    public String delete(@PathVariable Long communityId){
        communityService.deleteCommunity(communityId);
        return "redirect:/community";
    }

    //    게시글 좋아요
    @PostMapping("/community/like/{communityId}")
    public String like(@PathVariable Long communityId, @RequestParam("loginUserId") String loginUserId){
        communityService.selectLike(loginUserId,communityId);

        return "redirect:/community/detail/{communityId}";
    }
}
