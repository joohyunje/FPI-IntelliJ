package com.example.fpi.domain.dto.certify;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CardInfoFileDTO {
    private Long cardInfoFileId;
    private String cardInfoFileRoute; //파일경로
    private String cardInfoFileOriginal; //t-실제 파일명
    private Long proId;

}
