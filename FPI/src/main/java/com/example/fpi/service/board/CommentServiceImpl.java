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

// 게시글 아이디로 , 댓글 리스트 가져오기
    @Override
    public List<CommentListDTO> selectCommentList(Long communityId) {
        return commentMapper.selectCommentList(communityId);
    }

//    댓글 작성
    @Override
    public void saveComment(CommentDTO commentDTO) {

        commentMapper.insertComment(CommentVO.toEntity(commentDTO));
    }



//    댓글 수정
    @Override
    public void updateComment(CommentDTO commentDTO) {
        commentMapper.updateComment(CommentVO.toEntity(commentDTO));

    }

//    등록한 회원에게서 버튼이 뜨기때문에 회원아이디 가져오지 않아도됨
//    댓글 삭제
    @Override
    public void deleteComment(Long commentId) {

        commentMapper.deleteComment(commentId);
    }

}
