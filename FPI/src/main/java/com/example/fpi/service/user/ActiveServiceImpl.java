package com.example.fpi.service.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.mapper.user.ActiveListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActiveServiceImpl implements ActiveService {
    private final ActiveListMapper activeListMapper;
    //활동 게시글
    @Override
    public PagedResponse <UserCommunityListDTO> selectUserCommuList(String userId, String myName, int page, int pageSize,String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        int totalRequest = activeListMapper.countUserCommu(userId,myName);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);
        List<UserCommunityListDTO> communityList = activeListMapper.selectUserCommuList(userId,myName,startRow,endRow,sort);

        return new PagedResponse<>(communityList, page, totalPages, pageSize, totalRequest);
    }


    //    활동 댓글
    @Override
    public PagedResponse<CommentListDTO> selectUserCommentList(String userId, String myName, int page, int pageSize,String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        int totalRequest = activeListMapper.countUserComment(userId,myName);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);
        List <CommentListDTO> CommentList = activeListMapper.selectUserCommentList(userId,myName,startRow,endRow,sort);

        return new PagedResponse<>(CommentList, page, totalPages, pageSize, totalRequest);
    }

//    회원이 작성한 리뷰
    @Override
    public PagedResponse<ProReviewListDTO> selectUserWriteReview(String userId, int page, int pageSize,String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        int totalRequest = activeListMapper.countUserWriteReview(userId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);
        List <ProReviewListDTO> UserWriteReviewList = activeListMapper.selectUserWriteReview(userId,startRow,endRow,sort);
        return new PagedResponse<>(UserWriteReviewList, page, totalPages, pageSize, totalRequest);
    }

//    회원이 받은 리뷰
    @Override
    public PagedResponse<UserReviewListDTO> selectUserReceiveReview(String userId, int page, int pageSize,String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        int totalRequest = activeListMapper.countUserReceiveReview(userId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);
        List <UserReviewListDTO> UserReceiveReviewList = activeListMapper.selectUserReceiveReview(userId,startRow,endRow,sort);
        return new PagedResponse<>(UserReceiveReviewList, page, totalPages, pageSize, totalRequest);
    }



// 전문가가 작성한 리뷰
    @Override
    public PagedResponse<UserReviewListDTO> selectProWriteReview(Long proId, int page, int pageSize,String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        int totalRequest = activeListMapper.countProWriteReview(proId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);
        List <UserReviewListDTO> ProWriteReviewList = activeListMapper.selectProWriteReview(proId,startRow,endRow,sort);
        return new PagedResponse<>(ProWriteReviewList, page, totalPages, pageSize, totalRequest);
    }

//    전문가가 받은 리뷰
    @Override
    public PagedResponse<ProReviewListDTO> selectProReceiveReview(Long proId, int page, int pageSize,String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        int totalRequest = activeListMapper.countProReceiveReview(proId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);
        List <ProReviewListDTO> ProReceiveReviewList = activeListMapper.selectProReceiveReview(proId,startRow,endRow,sort);
        return new PagedResponse<>(ProReceiveReviewList, page, totalPages, pageSize, totalRequest);
    }

    @Override
    public PagedResponse<UserCommunityListDTO> selectLikeCommuList(String userId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        int totalRequest = activeListMapper.countLikeCommu(userId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);
        List<UserCommunityListDTO> communityList = activeListMapper.selectLikeCommuList(userId,startRow,endRow,sort);

        return new PagedResponse<>(communityList, page, totalPages, pageSize, totalRequest);
    }


}
