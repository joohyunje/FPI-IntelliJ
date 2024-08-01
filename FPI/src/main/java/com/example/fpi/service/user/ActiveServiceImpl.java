package com.example.fpi.service.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
import com.example.fpi.domain.dto.pro.ProReviewListDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
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
    @Transactional
    public List<UserCommunityListDTO> selectUserCommuList(String userId,String myName,int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        countUserCommu(userId,myName);

        return activeListMapper.selectUserCommuList(userId,myName,startRow,endRow);
    }

    @Override
    public int countUserCommu(String userId,String myName) {
        return activeListMapper.countUserCommu(userId,myName);
    }

    //    활동 댓글
    @Override
    @Transactional
    public List<CommentListDTO> selectUserCommentList(String userId, String myName, int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        countUserCommu(userId,myName);

        return activeListMapper.selectUserCommentList(userId,myName,startRow,endRow);
    }
    @Override
    public int countUserComment(String userId, String myName) {
        return activeListMapper.countUserComment(userId,myName);
    }

//    작성한 리뷰
    @Override
    @Transactional
    public List<ProReviewListDTO> selectUserWriteReview(String userId, int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        countUserWriteReview(userId);
        return activeListMapper.selectUserWriteReview(userId,startRow,endRow);
    }
    @Override
    public int countUserWriteReview(String userId) {
        return activeListMapper.countUserWriteReview(userId);
    }

    @Override
    @Transactional
    public List<UserReviewListDTO> selectUserReceiveReview(String userId, int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        countUserWriteReview(userId);
        return activeListMapper.selectUserReceiveReview(userId,startRow,endRow);
    }

    @Override
    public int countUserReceiveReview(String userId) {
        return activeListMapper.countUserReceiveReview(userId);
    }

    @Override
    @Transactional
    public List<UserReviewListDTO> selectProWriteReview(Long proId, int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        countProWriteReview(proId);
        return activeListMapper.selectProWriteReview(proId,startRow,endRow);
    }

    @Override
    public int countProWriteReview(Long proId) {
        return activeListMapper.countProWriteReview(proId);
    }

    @Override
    @Transactional
    public List<ProReviewListDTO> selectProReceiveReview(Long proId, int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        countProReceiveReview(proId);
        return activeListMapper.selectProReceiveReview(proId,startRow,endRow);
    }

    @Override
    public int countProReceiveReview(Long proId) {
        return activeListMapper.countProReceiveReview(proId);
    }
}
