package com.example.fpi.service.board;

import com.example.fpi.domain.dto.board.CommentDTO;
import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.vo.board.CommentVO;
import com.example.fpi.mapper.board.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<CommentListDTO> getCommentById(Long commentId) {
        return commentMapper.selectCommentById(commentId);
    }

    @Override
    public void saveComment(CommentDTO commentDTO) {
        commentMapper.insertComment(CommentVO.toEntity(commentDTO));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }

}
