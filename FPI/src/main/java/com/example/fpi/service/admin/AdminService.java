package com.example.fpi.service.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;
import com.example.fpi.domain.vo.admin.NotiVO;

import java.util.List;

public interface AdminService {
    //글 목록
    List<NotiDTO> getNotiList(int page, int pageSize);
    //글 목록
    List<FAQDTO> getFAQList(int page, int pageSize);
    //총 글 갯수
    int getNotiListCount();
    //총 글 갯수
    int getFAQListCount();
    //상세보기
    NotiDetailDTO getNotiById(Long NotiId);
    // 작성
    void saveNoti(NotiDTO noti);
    // 수정할 타겟
    NotiDetailDTO goUpdateNoti(Long notiId);
    //수정
    void updateNoti(NotiDTO noti);
    //삭제
    void deleteNoti(Long notiId);
}
