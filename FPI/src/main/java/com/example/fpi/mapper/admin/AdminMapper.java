package com.example.fpi.mapper.admin;

import com.example.fpi.domain.dto.admin.FaqDTO;
import com.example.fpi.domain.dto.admin.FaqDetailDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;
import com.example.fpi.domain.vo.admin.FaqVO;
import com.example.fpi.domain.vo.admin.NotiVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    // 게시글 작성시 사용 쿼리 - 임시
    long getSeq();

    // Noti 부분
    // 공지 게시판 목록
    List<NotiDTO> notiSelectAll(int startRow, int endRow);
    // 게시글 총 갯수
    int countNoti();
    // 게시글 상세보기
    NotiDetailDTO selectNotiDetail(Long notiId);
    // 왼쪽에 달리는 최신글
    List<NotiDTO> selectRecentNoti();
    // 게시글 작성
    void saveNoti(NotiDTO noti);
    // 게시글 수정
    void updateNoti(NotiVO notiVO);
    // 게시글 삭제
    void deleteNoti(Long notiId);




    // FAQ 부분
    // 공지 게시판 목록
    List<FaqDTO> faqSelectAll(int startRow, int endRow);
    // 게시글 총 갯수
    int countFaq();
    // 게시글 상세보기
    FaqDetailDTO selectFaqDetail(Long faqId);
    // 왼쪽에 달리는 최신글
    List<FaqDTO> selectRecentFaq();
    // 게시글 작성
    void saveFaq(FaqDTO faq);
    // 게시글 수정
    void updateFaq(FaqVO faqVO);
    // 게시글 삭제
    void deleteFaq(Long faqId);
}
