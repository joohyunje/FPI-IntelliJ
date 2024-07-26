package com.example.fpi.service.user;

import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.file.UserUploadFileDTO;
import com.example.fpi.domain.dto.pro.ProReviewDTO;
import com.example.fpi.domain.dto.user.*;
import com.example.fpi.domain.util.PagedResponse;
//import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.main.FormMapper;
import com.example.fpi.domain.vo.file.UserUploadFileVO;
import com.example.fpi.mapper.File.FileMapper;
import com.example.fpi.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final FileMapper fileMapper;

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

    //    유저 정보 변경
    @Override
    public void editUser(UserDTO dto) {
        userMapper.editUser(UserVO.toEntity(dto));
    }

    @Override
    public void editCategory(CategoryListDTO dto) {
        userMapper.editCategory(CategoryListVO.toEntity(dto));

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

    @Override
    public void updateCash(String userId, int userCash, int userPlusCash) {
        UserDTO dto = new UserDTO();
        dto.setUserId(userId);
        dto.setUserCash(userCash+userPlusCash);
        UserVO vo = UserVO.toEntity(dto);
        userMapper.updateCash(vo);

    }


    //  회원 견적 작성하기
    @Override
    @Transactional
    public void saveUserUpload(UserUploadDTO userUpload, List<MultipartFile> files) {
        Long userUploadId = userMapper.getUploadSeq();
        userUpload.setUserUploadId(userUploadId);
        userMapper.saveUserUpload(userUpload);

        saveUserUploadFile(userUploadId, files);
    }

    @Override
    public void saveUserUploadFile(Long userUploadId, List<MultipartFile> files) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);

        for (MultipartFile file : files) {
            // 방어코드
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename();
            String userUploadFileRouteName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;

            try {
                // 파일 저장 경로 설정
                Path directoryPath = Paths.get("/Users/hyunje/uploads/userUpload/" + datePath);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath); // 폴더가 없으면 생성
                }
                Path filePath = directoryPath.resolve(userUploadFileRouteName);
                // 파일 저장
                Files.copy(file.getInputStream(), filePath);

                UserUploadFileDTO userUploadFileDTO = new UserUploadFileDTO();
                userUploadFileDTO.setUserUploadId(userUploadId);
                userUploadFileDTO.setUserUploadFileOriginal(originalFileName);
                userUploadFileDTO.setUserUploadFileRoute(directoryPath + "/" + userUploadFileRouteName);

                fileMapper.insertUserUploadFile(UserUploadFileVO.toEntity(userUploadFileDTO)); // 파일 정보 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public UserLocationDTO selectUserLocation(String userId) {
        return userMapper.selectUserLocation(userId);
    }

    @Override
    public void userRequest(UserRequestDTO userRequest) {
        userMapper.userRequest(userRequest);
    }

    @Override
    public Long checkUserRequest(Long proUploadId, String userId) {
        return userMapper.checkUserRequest(proUploadId, userId);
    }

    @Override
    public void userWriteProReview(ProReviewDTO proReview) {
        userMapper.userWriteProReview(proReview);
    }

    @Override
    public void deleteProRequest(Long proRequestId) {
        userMapper.deleteProRequest(proRequestId);
    }


}
