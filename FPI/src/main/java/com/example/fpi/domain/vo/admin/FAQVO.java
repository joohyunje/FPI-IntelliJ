package com.example.fpi.domain.vo.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Getter
@ToString
@NoArgsConstructor
public class FAQVO {
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

    @Builder
    public FAQVO(Long FAQId, String FAQTitle, String FAQContent, LocalDateTime FAQRegisterDate, LocalDateTime FAQUpdateDate){
        this.FAQId=FAQId;
        this.FAQTitle=FAQTitle;
        this.FAQContent=FAQContent;
        this.FAQRegisterDate=FAQRegisterDate;
        this.FAQUpdateDate=FAQUpdateDate;
    }

    public static FAQVO toEntity(FAQDTO FAQDTO){
        return FAQVO.builder().FAQId(FAQDTO.getFAQId())
                .FAQTitle(FAQDTO.getFAQTitle())
                .FAQContent(FAQDTO.getFAQContent())
                .FAQRegisterDate(FAQDTO.getFAQRegisterDate())
                .FAQUpdateDate(FAQDTO.getFAQUpdateDate())
                .build();
    }
}