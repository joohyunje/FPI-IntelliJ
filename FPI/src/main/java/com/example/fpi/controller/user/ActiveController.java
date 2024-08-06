package com.example.fpi.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/activeList")
@RequiredArgsConstructor
public class ActiveController {

    //    마이페이지 활동내역 게시글
    @GetMapping("/community")
    public String communityList(){
        return "/user/activeList/community";}


     //    마이페이지 활동내역 댓글
    @GetMapping("/comment")
    public String activeListComment(){
         return "/user/activeList/comment";}

//    //    마이페이지 활동내역 작성한리뷰
    @GetMapping("/writeReview")
    public String activeListWriteReview(){
        return "/user/activeList/writeReview";
    }

    //    마이페이지 활동내역 받은 리뷰
    @GetMapping("/receiveReview")
    public String activeListReceiveReview(){
        return "/user/activeList/receiveReview";
    }

    //    마이페이지 좋아요게시글
    @GetMapping("/likeCommu")
    public String activeLikeCommunityList(){
        return "/user/activeList/likeList";
    }
}
