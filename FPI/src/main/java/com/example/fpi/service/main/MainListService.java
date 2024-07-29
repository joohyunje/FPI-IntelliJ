package com.example.fpi.service.main;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.pro.ProUploadListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MainListService {
    //    메인화면에 뿌려주는 최신 리스트
    List<CommunityDTO> mainCommunityList();
    List<ProUploadListDTO> proUploadList();
}
