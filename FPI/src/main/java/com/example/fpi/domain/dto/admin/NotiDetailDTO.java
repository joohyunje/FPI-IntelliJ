package com.example.fpi.domain.dto.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
// 상세보기에 들어갈거

@Component
@Data
public class NotiDetailDTO {
    // pk
    private Long NotiId;
    // 제목
    private String NotiTitle;
    // 내용
    private String NotiContent;
    // 작성일
    private LocalDateTime NotiRegisterDate;
    // 수정일
    private LocalDateTime NotiUpdateDate;
}
