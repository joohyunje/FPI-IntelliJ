package com.example.fpi.mapper.board;

import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    long getSeq();

    List<CommunityDTO> communitySelectAll(int startRow, int endRow);

    int countCommunity();

    CommunityDetailDTO selectCommunityDetail(Long communityId);
}
