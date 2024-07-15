package com.example.fpi.domain.vo.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
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
public class NotiVO {
    // pk
    private Long notiId;
    // 제목
    private String notiTitle;
    // 내용
    private String notiContent;
    // 작성일
    private LocalDateTime notiRegisterDate;
    // 수정일
    private LocalDateTime notiUpdateDate;

    @Builder
    public NotiVO(Long notiId, String notiTitle, String notiContent, LocalDateTime notiRegisterDate, LocalDateTime notiUpdateDate){
        this.notiId=notiId;
        this.notiTitle=notiTitle;
        this.notiContent=notiContent;
        this.notiRegisterDate=notiRegisterDate;
        this.notiUpdateDate=notiUpdateDate;
    }

    public static NotiVO toEntity(NotiDTO notiDTO){
        return NotiVO.builder().notiId(notiDTO.getNotiId())
                .notiTitle(notiDTO.getNotiTitle())
                .notiContent(notiDTO.getNotiContent())
                .notiRegisterDate(notiDTO.getNotiRegisterDate())
                .notiUpdateDate(notiDTO.getNotiUpdateDate())
                .build();
    }
}
