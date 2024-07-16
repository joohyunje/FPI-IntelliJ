package com.example.fpi.domain.dto.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
// 상세보기에 들어갈거

@Component
@Data
public class FAQDetailDTO {
    // pk
    private Long faqId;
    // 제목
    private String faqTitle;
    // 내용
    private String faqContent;
    // 작성일
    private LocalDateTime faqRegisterDate;
    // 수정일
    private LocalDateTime faqUpdateDate;
}
