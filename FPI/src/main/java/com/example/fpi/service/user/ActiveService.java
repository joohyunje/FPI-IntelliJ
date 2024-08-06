package com.example.fpi.service.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
import com.example.fpi.domain.util.PagedResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActiveService {
    //    활동내역 중 게시글 페이징
    PagedResponse<UserCommunityListDTO> selectUserCommuList(String userId, String myName, int page, int pageSize, String sort);

    //    활동내역 중 댓글 페이징
    PagedResponse<CommentListDTO> selectUserCommentList(String userId, String myName, int page, int pageSize,String sort);

    //    회원이 작성한 리뷰
    PagedResponse<ProReviewListDTO> selectUserWriteReview(String userId, int page, int pageSize,String sort);

    //    회원이 전문가에게  받은 리뷰
    PagedResponse<UserReviewListDTO> selectUserReceiveReview(String userId, int page, int pageSize,String sort);

    //    전문가가 작성한 리뷰
    PagedResponse<UserReviewListDTO> selectProWriteReview(Long proId, int page, int pageSize,String sort);

    //    전문가가 회원에게  받은 리뷰
    PagedResponse<ProReviewListDTO> selectProReceiveReview(Long proId, int page, int pageSize,String sort);

//    좋아요 목록
    PagedResponse<UserCommunityListDTO> selectLikeCommuList(String userId,int page, int pageSize,String sort);

}
