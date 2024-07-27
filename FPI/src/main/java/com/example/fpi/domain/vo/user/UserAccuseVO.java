package com.example.fpi.domain.vo.user;

import com.example.fpi.domain.dto.user.UserAccuseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@ToString
public class UserAccuseVO {

    private Long userAccuseId;
    private String userAccuseContent;
    private String userId;
    private Long proId;

    @Builder
    public UserAccuseVO(Long userAccuseId, String userAccuseContent, String userId, Long proId) {
        this.userAccuseId = userAccuseId;
        this.userAccuseContent = userAccuseContent;
        this.userId = userId;
        this.proId = proId;
    }

    public static UserAccuseVO toEntity(UserAccuseDTO userAccuseDTO) {
        return UserAccuseVO.builder().userAccuseId(userAccuseDTO.getUserAccuseId())
                .userAccuseContent(userAccuseDTO.getUserAccuseContent())
                .userId(userAccuseDTO.getUserId())
                .proId(userAccuseDTO.getProId())
                .build();
    }

}
