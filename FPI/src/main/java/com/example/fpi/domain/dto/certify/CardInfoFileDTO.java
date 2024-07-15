package com.example.fpi.domain.dto.certify;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CardInfoFileDTO {
    private Long cardInfoFileId;
    private String cardInfoFileRoute;
    private String cardInfoFileOriginal;
    private String cardFileSaved;
    private Long cardInfoId;

}
