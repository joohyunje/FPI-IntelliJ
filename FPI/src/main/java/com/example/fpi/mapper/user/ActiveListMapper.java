package com.example.fpi.mapper.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActiveListMapper {
//    마이페이지 활동목록 가져오기

//    작성한 게시글 목록
    List<UserCommunityListDTO> selectUserCommuList(String userId,String myName, int startRow, int endRow);
    int countUserCommu(String userId,String myName);

//    작성한 댓글목록
    List<CommentListDTO> selectUserCommentList(String userId, String myName, int startRow, int endRow);
    int countUserComment(String userId,String myName);

//    회원이 작성한 리뷰
    List<ProReviewListDTO> selectUserWriteReview(String userId, int startRow, int endRow);
    int countUserWriteReview(String userId);

    //    회원이 전문가에게  받은 리뷰
    List<UserReviewListDTO> selectUserReceiveReview(String userId, int startRow, int endRow);
    int countUserReceiveReview(String userId);


    //    전문가가 작성한 리뷰
    List<UserReviewListDTO> selectProWriteReview(Long proId, int startRow, int endRow);
    int countProWriteReview(Long ProId);

    //    전문가가 회원에게  받은 리뷰
    List<ProReviewListDTO> selectProReceiveReview(Long proId, int startRow, int endRow);
    int countProReceiveReview(Long proId);
}
