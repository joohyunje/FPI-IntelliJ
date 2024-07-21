package com.example.fpi.service.board;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityDetailDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.mapper.board.CommunityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;

    @Override
    public List<CommunityDTO> getCommunityList(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        return communityMapper.communitySelectAll(startRow, endRow);
    }

    @Override
    public int getCommunityListCount() {
        return communityMapper.countCommunity();
    }

    @Override
    public CommunityDetailDTO getCommunityById(Long communityId, CustomOAuth2User customOAuth2User) {
        CommunityDetailDTO community= communityMapper.selectCommunityDetail(communityId);

        return community;
    }
}
