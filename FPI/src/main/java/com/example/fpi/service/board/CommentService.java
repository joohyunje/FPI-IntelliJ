package com.example.fpi.service.board;

import com.example.fpi.domain.dto.board.CommentDTO;
import com.example.fpi.domain.dto.board.CommentListDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CommentService {
//   해당 게시글의 댓글 리스트 가져오기
    List<CommentListDTO> selectCommentList(Long communityId);
//    댓글 작성
    void saveComment(CommentDTO commentDTO);
//    댓글 수정
    void updateComment(CommentDTO commentDTO);
//    댓글 삭제
    void deleteComment(Long commentId);
}