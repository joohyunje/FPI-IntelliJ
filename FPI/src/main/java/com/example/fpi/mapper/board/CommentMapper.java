package com.example.fpi.mapper.board;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.vo.board.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentListDTO> selectCommentById(Long commentId);
    void insertComment(CommentVO commentVO);
    void deleteComment(Long commentId);
}
