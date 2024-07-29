package com.example.fpi.service.user;

import com.example.fpi.domain.dto.board.CommentListDTO;
import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.UserCommunityListDTO;
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
}
