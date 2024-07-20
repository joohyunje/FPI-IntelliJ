package com.example.fpi.domain.vo.admin;

import com.example.fpi.domain.dto.admin.FaqDTO;
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
public class FaqVO {
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

    @Builder
    public FaqVO(Long faqId, String faqTitle, String faqContent, LocalDateTime faqRegisterDate, LocalDateTime faqUpdateDate){
        this.faqId=faqId;
        this.faqTitle=faqTitle;
        this.faqContent=faqContent;
        this.faqRegisterDate=faqRegisterDate;
        this.faqUpdateDate=faqUpdateDate;
    }

    public static FaqVO toEntity(FaqDTO faqDTO){
        return FaqVO.builder().faqId(faqDTO.getFaqId())
                .faqTitle(faqDTO.getFaqTitle())
                .faqContent(faqDTO.getFaqContent())
                .faqRegisterDate(faqDTO.getFaqRegisterDate())
                .faqUpdateDate(faqDTO.getFaqUpdateDate())
                .build();
    }
}