package com.example.fpi.controller.pro;

import com.example.fpi.service.pro.ProService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardRestController {

    private final ProService proService;

    @GetMapping("/{proId}")
    public ResponseEntity<?> selectCardInfoFile(@PathVariable Long proId) {
        return  ResponseEntity.ok(proService.selectCard(proId));
    }
    @DeleteMapping("{cardInfoId}")
    public ResponseEntity<?> deleteCardPhotoFile(@PathVariable Long cardInfoId) {
        proService.clickDeleteCard(cardInfoId);
        return  ResponseEntity.ok().build();
    }
}
