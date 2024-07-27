package com.example.fpi.domain.vo.pro;

import com.example.fpi.domain.dto.pro.ProAccuseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@ToString
public class ProAccuseVO {

    private Long userAccuseId;
    private String userAccuseContent;
    private String userId;
    private Long proId;

    @Builder
    public ProAccuseVO(Long userAccuseId, String userAccuseContent, String userId, Long proId) {
        this.userAccuseId = userAccuseId;
        this.userAccuseContent = userAccuseContent;
        this.userId = userId;
        this.proId = proId;
    }

    public static ProAccuseVO toEntity(ProAccuseDTO proAccuseDTO) {
        return ProAccuseVO.builder().userAccuseId(proAccuseDTO.getUserAccuseId())
                .userAccuseContent(proAccuseDTO.getUserAccuseContent())
                .userId(proAccuseDTO.getUserId())
                .proId(proAccuseDTO.getProId())
                .build();
    }

}
