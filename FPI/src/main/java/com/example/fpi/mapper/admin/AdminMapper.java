package com.example.fpi.mapper.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;
import com.example.fpi.domain.vo.admin.NotiVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    // 게시글 작성시 사용 쿼리 - 임시
    long getSeq();

    // Noti 부분
    // 공지 게시판 목록
    List<NotiDTO> NotiSelectAll(int startRow, int endRow);
    // 게시글 총 갯수
    int countNoti();
    // 게시글 상세보기
    NotiDetailDTO selectNotiDetail(Long NotiId);
    // 게시글 작성
    void saveNoti(NotiDTO noti);
    // 게시글 수정
    void updateNoti(NotiVO notiVO);
    // 게시글 삭제
    void deleteNoti(Long notiId);




    //FAQ 부분
    // 공지 게시글 목록
    List<FAQDTO> FAQSelectAll(int startRow, int endRow);
    // 게시글 총 갯수
    int countFAQ();
}
