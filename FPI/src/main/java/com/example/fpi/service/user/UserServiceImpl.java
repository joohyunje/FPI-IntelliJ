package com.example.fpi.service.user;

import com.example.fpi.domain.dto.user.UserReceivedReqListDTO;
import com.example.fpi.domain.dto.user.UserRequestDetailDTO;
import com.example.fpi.domain.dto.user.UserReviewListDTO;
import com.example.fpi.domain.dto.user.UserSendReqListDTO;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    //    받은 요청 목록
    @Override
    public PagedResponse<UserReceivedReqListDTO> selectReceivedReq(int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = userMapper.countReceivedRequest();
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<UserReceivedReqListDTO> requests = userMapper.selectReceivedReq(startRow, endRow, sort);
        System.out.println(requests.get(0));
        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }
    //  보낸 요청 목록
    @Override
    public PagedResponse<UserSendReqListDTO> selectSendReq(int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = userMapper.countSendRequest();
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<UserSendReqListDTO> requests = userMapper.selectSendReq(startRow, endRow, sort);

        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }

    @Override
    public UserRequestDetailDTO selectUserReqDetail(Long userRequestId) {
        return userMapper.selectUserReqDetail(userRequestId);
    }

    @Override
    public List<UserReviewListDTO> selectUserReview(String userId) {
        return userMapper.selectUserReview(userId);
    }
}
