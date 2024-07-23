package com.example.fpi.service.board;

import com.example.fpi.domain.dto.board.CommentDTO;
import com.example.fpi.domain.dto.board.CommentListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<CommentListDTO> getCommentById(Long boardId);
    void saveComment(CommentDTO commentDTO);
    void deleteComment(Long commentId);
}