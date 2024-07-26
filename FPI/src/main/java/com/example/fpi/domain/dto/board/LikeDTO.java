package com.example.fpi.domain.dto.board;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LikeDTO {
    private Long likeId;
    private String userId;
    private Long  communityId;
}
