package com.example.fpi.service.admin;

import com.example.fpi.domain.dto.admin.FaqDTO;
import com.example.fpi.domain.dto.admin.FaqDetailDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;

import java.util.List;

public interface AdminService {
    //글 목록
    List<NotiDTO> getNotiList(int page, int pageSize);
    //총 글 갯수
    int getNotiListCount();
    //상세보기
    NotiDetailDTO getNotiById(Long notiId);
    //왼쪽 최신글 가져오기
    List<NotiDTO> getRecentNoti();
    // 작성
    void saveNoti(NotiDTO noti);
    // 수정할 타겟
    NotiDetailDTO goUpdateNoti(Long notiId);
    //수정
    void updateNoti(NotiDTO noti);
    //삭제
    void deleteNoti(Long notiId);



    //글 목록
    List<FaqDTO> getFaqList(int page, int pageSize);
    //총 글 갯수
    int getFaqListCount();
    //상세보기
    FaqDetailDTO getFaqById(Long faqId);
    //왼쪽 최신글 가져오기
    List<FaqDTO> getRecentFaq();
    // 작성
    void saveFaq(FaqDTO faq);
    // 수정할 타겟
    FaqDetailDTO goUpdateFaq(Long faqId);
    //수정
    void updateFaq(FaqDTO faq);
    //삭제
    void deleteFaq(Long faqId);

}
