package com.example.fpi.domain.dto.file;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProCardInfoFileDTO {

    private Long cardInfoFileId;
    private String cardInfoFileRoute;
    private String cardInfoFileOriginal;
    private Long proId;

}
