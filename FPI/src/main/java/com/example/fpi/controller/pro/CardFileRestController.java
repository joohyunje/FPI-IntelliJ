package com.example.fpi.controller.pro;

import com.example.fpi.service.file.FileService;
import com.example.fpi.service.pro.ProService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cardfiles")
public class CardFileRestController {

    private final ProService proService;
    private final FileService fileService;
    @GetMapping("/{proId}")
    // ok()안에 있는것과 ?가 자동 매칭됨
    public ResponseEntity<?> selectCardInfoFile(@PathVariable Long proId) {
        return  ResponseEntity.ok(proService.selectCardInfoFile(proId));
    }
    @DeleteMapping("{cardInfoFileId}")
    public ResponseEntity<?> deleteCardPhotoFile(@PathVariable Long cardInfoFileId) throws IOException {
        fileService.deleteCardPhotoFile(cardInfoFileId);
        return  ResponseEntity.ok().build();
    }
}
