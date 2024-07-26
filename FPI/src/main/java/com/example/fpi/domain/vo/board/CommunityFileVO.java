package com.example.fpi.domain.vo.board;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityFileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@NoArgsConstructor
public class CommunityFileVO {
    private Long communityFileId;
    private String communityFileRoute;
    private String communityFileOriginal;
    private Long communityId;

    @Builder
    public CommunityFileVO(Long communityFileId, String communityFileRoute, String communityFileOriginal, Long communityId) {
        this.communityFileId = communityFileId;
        this.communityFileRoute = communityFileRoute;
        this.communityFileOriginal = communityFileOriginal;
        this.communityId = communityId;
    }

    public static CommunityFileVO toEntity(CommunityFileDTO communityFileDTO) {
        return CommunityFileVO.builder().communityFileId(communityFileDTO.getCommunityFileId())
                .communityFileRoute(communityFileDTO.getCommunityFileRoute())
                .communityFileOriginal(communityFileDTO.getCommunityFileOriginal())
                .communityId(communityFileDTO.getCommunityId())
                .build();
    }

}
