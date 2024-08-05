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

//    전문가 마이페이지에서 자격증 사진 select
    @GetMapping("/{proId}")
    public ResponseEntity<?> selectCardInfoFile(@PathVariable Long proId) {
        return  ResponseEntity.ok(proService.selectCardInfoFile(proId));
    }

//    클릭이벤트로 삭제구현
    @DeleteMapping("{cardInfoFileId}")
    public ResponseEntity<?> deleteCardPhotoFile(@PathVariable Long cardInfoFileId) throws IOException {
        fileService.deleteCardPhotoFile(cardInfoFileId);
        return  ResponseEntity.ok().build();
    }
}
