package com.example.fpi.controller.pro;

import com.example.fpi.domain.dto.pro.ProReceivedReqListDTO;
import com.example.fpi.domain.dto.pro.ProSendReqListDTO;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.service.pro.ProService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proRest")
public class ProRestController {

    private final ProService proService;

    @GetMapping("/received")
    public ResponseEntity<PagedResponse<ProReceivedReqListDTO>> getReceivedRequestsList(@RequestParam(defaultValue = "1") int page,
                                                                                        @RequestParam(defaultValue = "6") int size,
                                                                                        @RequestParam(defaultValue = "") String sort) {


//        PagedResponse<BoardListDTO> sortedBoards = switch (sort){
//            case "oldest" -> boardService.selectAllByDateASC(page, size);
//            case "views" -> boardService.selectAllByViews(page, size);
//            default -> boardService.selectAllByDateDESC(page, size);
//        };

        return ResponseEntity.ok(proService.selectReceivedReq(page, size, sort));
    }

    @GetMapping("/send")
    public ResponseEntity<PagedResponse<ProSendReqListDTO>> getSendRequestsList(@RequestParam(defaultValue = "1") int page,
                                                                                @RequestParam(defaultValue = "6") int size,
                                                                                @RequestParam(defaultValue = "") String sort) {


//        PagedResponse<BoardListDTO> sortedBoards = switch (sort){
//            case "oldest" -> boardService.selectAllByDateASC(page, size);
//            case "views" -> boardService.selectAllByViews(page, size);
//            default -> boardService.selectAllByDateDESC(page, size);
//        };

        return ResponseEntity.ok(proService.selectSendReq(page, size, sort));
    }



}
