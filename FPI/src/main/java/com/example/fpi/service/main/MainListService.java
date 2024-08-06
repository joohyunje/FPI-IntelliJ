package com.example.fpi.service.main;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityDetailDTO;
import com.example.fpi.domain.dto.pro.ProUploadListDTO;
import com.example.fpi.domain.dto.user.UserUploadListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MainListService {
    //    메인화면에 뿌려주는 최신 리스트
    List<CommunityDetailDTO> mainCommunityList();
    List<CommunityDetailDTO> mainProList();//오늘의 전문가팁
    List<ProUploadListDTO> proUploadList();
    List<UserUploadListDTO> userUploadList();
}
