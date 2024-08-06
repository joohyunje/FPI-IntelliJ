package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
import com.example.fpi.domain.dto.pro.ProReceivedReqListDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.service.pro.ProService;
import com.example.fpi.service.user.ActiveService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/active")
public class ActiveRestController {

//    마이페이지 활동내역 , 게시글
    private final ActiveService activeService;
    private final ProService proService;
    @GetMapping("/community")
    public ResponseEntity<PagedResponse<UserCommunityListDTO>> activeRestListcommunity(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                       HttpSession session,
                                                                                       @RequestParam(value="page", defaultValue = "1")int page,
                                                                                       @RequestParam (value="pageSize", defaultValue = "8")int size,
                                                                                       @RequestParam String sort){
        String userId = customOAuth2User.getUserId();
        String loginName = (String) session.getAttribute("loginName");
        String myName = (loginName != null) ? loginName :(String) session.getAttribute("proName");
        System.out.println(sort);

        return ResponseEntity.ok(activeService.selectUserCommuList(userId,myName ,page, size, sort));
    }

//    댓글
    @GetMapping("/comment")
    public ResponseEntity<PagedResponse<CommentListDTO>> activeRestListComment(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                           HttpSession session,
                                                                           @RequestParam(value="page", defaultValue = "1")int page,
                                                                           @RequestParam (value="pageSize", defaultValue = "8")int size,
                                                                           @RequestParam String sort) {
        String userId = customOAuth2User.getUserId();
        String loginName = (String) session.getAttribute("loginName");
        String proName = (String) session.getAttribute("proName");
        String myName = (loginName != null) ? loginName : proName;

        return ResponseEntity.ok(activeService.selectUserCommentList(userId, myName, page, size, sort));
    }

//      작성한 리뷰!! html에서 session에 담긴 proName이 null인지에 따라 ajax 다르게 함, 받은리뷰도 똑같음
//    회원이 작성한 리뷰
    @GetMapping("/user/writeReview")
    public ResponseEntity<PagedResponse<ProReviewListDTO>> activeRestUserListWriteReview(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                        @RequestParam(value="page", defaultValue = "1")int page,
                                                                                        @RequestParam (value="pageSize", defaultValue = "8")int size,
                                                                                        @RequestParam String sort) {
        String userId = customOAuth2User.getUserId();

        return ResponseEntity.ok(activeService.selectUserWriteReview(userId, page, size, sort));

    }

//    전문가가 작성한리뷰
    @GetMapping("/pro/writeReview")
    public ResponseEntity<PagedResponse<UserReviewListDTO>> activeRestProListWriteReview(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                         @RequestParam(value="page", defaultValue = "1")int page,
                                                                                         @RequestParam (value="pageSize", defaultValue = "8")int size,
                                                                                         @RequestParam String sort) {
        String userId = customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);

        return ResponseEntity.ok(activeService.selectProWriteReview(proId, page, size, sort));
    }

    //    회원이 받은 리뷰
    @GetMapping("/user/receiveReview")
    public ResponseEntity<PagedResponse<UserReviewListDTO>> activeRestUserListReceiveReview(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                             @RequestParam(value="page", defaultValue = "1")int page,
                                                                                             @RequestParam (value="pageSize", defaultValue = "8")int size,
                                                                                             @RequestParam String sort) {
        String userId = customOAuth2User.getUserId();

        return ResponseEntity.ok(activeService.selectUserReceiveReview(userId, page, size, sort));

    }

    //    전문가가 받은 리뷰
    @GetMapping("/pro/receiveReview")
    public ResponseEntity<PagedResponse<ProReviewListDTO>> activeRestProListReceiveReview(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                             @RequestParam(value="page", defaultValue = "1")int page,
                                                                                             @RequestParam (value="pageSize", defaultValue = "8")int size,
                                                                                             @RequestParam String sort) {
        String userId = customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);

        return ResponseEntity.ok(activeService.selectProReceiveReview(proId, page, size, sort));
    }

    @GetMapping("/likeCommu")
    public ResponseEntity<PagedResponse<UserCommunityListDTO>> activeRestLikeCommunityList(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                       HttpSession session,
                                                                                       @RequestParam(value="page", defaultValue = "1")int page,
                                                                                       @RequestParam (value="pageSize", defaultValue = "8")int size,
                                                                                       @RequestParam String sort){
        String userId = customOAuth2User.getUserId();

        return ResponseEntity.ok(activeService.selectLikeCommuList(userId,page, size, sort));
    }




}
