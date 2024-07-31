package com.example.fpi.service.main;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityDetailDTO;
import com.example.fpi.domain.dto.pro.ProUploadListDTO;
import com.example.fpi.domain.dto.user.UserUploadListDTO;
import com.example.fpi.mapper.main.MainListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MainListServiceImpl implements MainListService {
    private final MainListMapper mainListMapper;
    @Override
    public List<CommunityDetailDTO> mainCommunityList() {
        return mainListMapper.mainCommunityList();
    }

    @Override
    public List<ProUploadListDTO> proUploadList() {
        return mainListMapper.proUploadList();
    }

    @Override
    public List<UserUploadListDTO> userUploadList() {
        return mainListMapper.userUploadList();
    }
}
