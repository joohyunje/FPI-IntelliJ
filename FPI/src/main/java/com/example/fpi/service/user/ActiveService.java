package com.example.fpi.service.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActiveService {
    //    활동내역 중 게시글 페이징
    List<UserCommunityListDTO> selectUserCommuList(String userId,String myName, int page, int pageSize);
    int countUserCommu(String userId,String myName);
    //    활동내역 중 댓글 페이징
    List<CommentListDTO> selectUserCommentList(String userId, String myName, int page, int pageSize);
    int countUserComment(String userId,String myName);

    //    회원이 작성한 리뷰
    List<ProReviewListDTO> selectUserWriteReview(String userId, int page, int pageSize);
    int countUserWriteReview(String userId);

    //    회원이 전문가에게  받은 리뷰
    List<UserReviewListDTO> selectUserReceiveReview(String userId, int page, int pageSize);
    int countUserReceiveReview(String userId);
}
