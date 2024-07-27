package com.example.fpi.controller.pro;

import com.example.fpi.service.file.FileService;
import com.example.fpi.service.pro.ProService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardRestController {

    private final ProService proService;

    @GetMapping("/{proId}")
    // ok()안에 있는것과 ?가 자동 매칭됨
    public ResponseEntity<?> selectCardInfoFile(@PathVariable Long proId) {
        return  ResponseEntity.ok(proService.selectCard(proId));
    }
    @DeleteMapping("{cardInfoId}")
    public ResponseEntity<?> deleteCardPhotoFile(@PathVariable Long cardInfoId) {
        proService.clickDeleteCard(cardInfoId);
        return  ResponseEntity.ok().build();
    }
}
