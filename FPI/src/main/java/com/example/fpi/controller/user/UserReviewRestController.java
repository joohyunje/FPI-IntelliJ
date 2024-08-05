package com.example.fpi.controller.user;

import com.example.fpi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userReviews")
@RequiredArgsConstructor
public class UserReviewRestController {

    private final UserService userService;

    // 회원이 받은 리뷰 동적
    @GetMapping("/{userId}")
    public ResponseEntity<?> getReviews(@PathVariable String userId) {
        return ResponseEntity.ok(userService.selectUserReview(userId));
    }


}
