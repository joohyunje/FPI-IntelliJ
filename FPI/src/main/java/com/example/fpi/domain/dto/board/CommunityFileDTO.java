package com.example.fpi.domain.dto.board;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommunityFileDTO {
    private Long communityFileId;
    private String communityFileRoute;
    private String communityFileOriginal;
    private Long communityId;

}
