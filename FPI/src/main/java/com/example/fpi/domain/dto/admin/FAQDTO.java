package com.example.fpi.domain.dto.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class FAQDTO {
    // pk
    private Long FAQId;
    // 제목
    private String FAQTitle;
    // 내용
    private String FAQContent;
    // 작성일
    private LocalDateTime FAQRegisterDate;
    // 수정일
    private LocalDateTime FAQUpdateDate;
}
