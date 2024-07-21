package com.example.fpi.service.board;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityDetailDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommunityService {

    List<CommunityDTO> getCommunityList(int page, int pageSize);

    int getCommunityListCount();

    CommunityDetailDTO getCommunityById(Long communityId, CustomOAuth2User customOAuth2User);
}

