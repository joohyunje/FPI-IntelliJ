package com.example.fpi.service.user;

import com.example.fpi.domain.dto.user.*;
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
    public PagedResponse<UserReceivedReqListDTO> selectReceivedReq(String userId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = userMapper.countReceivedRequest(userId);
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<UserReceivedReqListDTO> requests = userMapper.selectReceivedReq(userId, startRow, endRow, sort);
        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }
    //  보낸 요청 목록
    @Override
    public PagedResponse<UserSendReqListDTO> selectSendReq(String userId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = userMapper.countSendRequest(userId);
        int totalPages = (int) Math.ceil((double)totalRequest/pageSize);

        List<UserSendReqListDTO> requests = userMapper.selectSendReq(userId, startRow, endRow, sort);

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

//유저 정보 조회
    @Override
    public UserDTO detailUser(String userId) {

        return userMapper.detailUser(userId);
    }


//    유저 삭제 시, 이름 입력 비교를 위해
//    DB에서 이름을 가져오기
    @Override
    public String getUserName(String userId) {
        return userMapper.findByUserId(userId).getUserName();
    }


//    유저 정보 삭제
    @Override
    public void deleteUser(String userId,String userName) {

        userMapper.deleteUser(userId,userName);
    }
// 전문가 탈퇴시 approval 변경
    @Override
    public void editApproval(String userId) {
        userMapper.editApproval(userId);
    }

    @Override
    public PagedResponse<UserUploadListDTO> selectUserUploadList(int page, int pageSize, String search) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalUploads = userMapper.countUserUpload(search);
        int totalPages = (int) Math.ceil((double)totalUploads/pageSize);

        List<UserUploadListDTO> uploads = userMapper.selectUserUploadList(startRow, endRow, search);

        return new PagedResponse<>(uploads, page, totalPages, pageSize, totalUploads);
    }

    @Override
    public UserUploadDetailDTO selectUserUploadDetail(Long userUploadId) {
        return userMapper.selectUserUploadDetail(userUploadId);
    }


}
