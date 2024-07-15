package com.example.fpi.domain.dto.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class NotiDTO {
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