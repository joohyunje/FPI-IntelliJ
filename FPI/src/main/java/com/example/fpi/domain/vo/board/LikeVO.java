package com.example.fpi.domain.vo.board;

import com.example.fpi.domain.dto.board.LikeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class LikeVO {
    private Long likeId;
    private String userId;
    private Long  communityId;

    @Builder
    public LikeVO(Long likeId, String userId, Long  communityId) {
        this.likeId = likeId;
        this.userId = userId;
        this.communityId = communityId;
    }

    public static LikeVO toEntity(LikeDTO likeDTO) {
        return LikeVO.builder().likeId(likeDTO.getLikeId())
                .userId(likeDTO.getUserId())
                .communityId(likeDTO.getCommunityId())
                .build();
    }
}
