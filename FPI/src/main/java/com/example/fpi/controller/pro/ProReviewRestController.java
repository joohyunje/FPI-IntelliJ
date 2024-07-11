package com.example.fpi.controller.pro;

import com.example.fpi.service.pro.ProService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proReviews")
@RequiredArgsConstructor
public class ProReviewRestController {

    private final ProService proService;

    @GetMapping("/{proId}")
    public ResponseEntity<?> getReviews(@PathVariable Long proId) {
        return ResponseEntity.ok(proService.selectProReview(proId));
    }



}
