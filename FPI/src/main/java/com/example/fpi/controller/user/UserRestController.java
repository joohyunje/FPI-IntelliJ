package com.example.fpi.controller.user;

import com.example.fpi.domain.dto.user.UserReceivedReqListDTO;
import com.example.fpi.domain.dto.user.UserSendReqListDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userRest")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/received")
    public ResponseEntity<PagedResponse<UserReceivedReqListDTO>> getReceivedRequestsList(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                              @RequestParam(defaultValue = "1") int page,
                                                                              @RequestParam(defaultValue = "6") int size,
                                                                              @RequestParam(defaultValue = "") String sort) {
        String userId = customOAuth2User.getUserId();
        System.out.println(customOAuth2User);
        System.out.println(userId);


        return ResponseEntity.ok(userService.selectReceivedReq(userId, page, size, sort));
    }

    @GetMapping("/send")
    public ResponseEntity<PagedResponse<UserSendReqListDTO>> getSendRequestsList(@RequestParam(defaultValue = "1") int page,
                                                                                 @RequestParam(defaultValue = "6") int size,
                                                                                 @RequestParam(defaultValue = "") String sort) {


//        PagedResponse<BoardListDTO> sortedBoards = switch (sort){
//            case "oldest" -> boardService.selectAllByDateASC(page, size);
//            case "views" -> boardService.selectAllByViews(page, size);
//            default -> boardService.selectAllByDateDESC(page, size);
//        };



        return ResponseEntity.ok(userService.selectSendReq(page, size, sort));
    }



}
