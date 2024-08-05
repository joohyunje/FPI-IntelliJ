package com.example.fpi.controller.pro;

import com.example.fpi.domain.dto.pro.ProReceivedReqListDTO;
import com.example.fpi.domain.dto.pro.ProSendReqListDTO;
import com.example.fpi.domain.dto.user.UserUploadListDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.service.pro.ProService;
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
@RequestMapping("/proRest")
public class ProRestController {

    private final ProService proService;
    private final UserService userService;

    // 전문가가 받은 요청 동적
    @GetMapping("/received")
    public ResponseEntity<PagedResponse<ProReceivedReqListDTO>> getReceivedRequestsList(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                        @RequestParam(defaultValue = "1") int page,
                                                                                        @RequestParam(defaultValue = "6") int size,
                                                                                        @RequestParam(defaultValue = "") String sort) {
        String userId = customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);

//        PagedResponse<BoardListDTO> sortedBoards = switch (sort){
//            case "oldest" -> boardService.selectAllByDateASC(page, size);
//            case "views" -> boardService.selectAllByViews(page, size);
//            default -> boardService.selectAllByDateDESC(page, size);
//        };

        return ResponseEntity.ok(proService.selectReceivedReq(proId, page, size, sort));
    }

    // 전문가가 보낸 요청 동적
    @GetMapping("/send")
    public ResponseEntity<PagedResponse<ProSendReqListDTO>> getSendRequestsList(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                                                @RequestParam(defaultValue = "1") int page,
                                                                                @RequestParam(defaultValue = "6") int size,
                                                                                @RequestParam(defaultValue = "") String sort) {

        String userId = customOAuth2User.getUserId();
        Long proId = proService.selectProId(userId);

//        PagedResponse<BoardListDTO> sortedBoards = switch (sort){
//            case "oldest" -> boardService.selectAllByDateASC(page, size);
//            case "views" -> boardService.selectAllByViews(page, size);
//            default -> boardService.selectAllByDateDESC(page, size);
//        };

        return ResponseEntity.ok(proService.selectSendReq(proId, page, size, sort));
    }

    // 회원 찾기 동적
    @GetMapping("/UserFind")
    public ResponseEntity<PagedResponse<UserUploadListDTO>> getBoardList(@RequestParam(defaultValue = "1") int page,
                                                                         @RequestParam(defaultValue = "7") int size,
                                                                         @RequestParam String search,
                                                                         @RequestParam String searchType,
                                                                         @RequestParam String searchSubject) {


        System.out.println("여기");

//        PagedResponse<BoardListDTO> sortedBoards = switch (sort){
//            case "oldest" -> boardService.selectAllByDateASC(page, size);
//            case "views" -> boardService.selectAllByViews(page, size);
//            default -> boardService.selectAllByDateDESC(page, size);
//        };

        return ResponseEntity.ok(userService.selectUserUploadList(page, size, search, searchType, searchSubject));
    }


}
