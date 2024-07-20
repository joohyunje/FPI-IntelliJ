package com.example.fpi.domain.dto.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class FaqDetailDTO {
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
