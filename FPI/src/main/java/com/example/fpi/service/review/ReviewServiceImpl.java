//package com.example.fpi.service.review;
//
//import com.example.fpi.domain.dto.pro.ProReviewDTO;
//import com.example.fpi.domain.dto.user.UserReviewDTO;
//import com.example.fpi.domain.vo.pro.ProReviewVO;
//import com.example.fpi.mapper.pro.ProMapper;
//import com.example.fpi.mapper.user.UserMapper;
//import com.example.fpi.service.pro.ProService;
//import com.example.fpi.service.user.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class ReviewServiceImpl implements ReviewService {
//
//    private final ProMapper proMapper;
//    private final ProService proService;
//    private final UserMapper userMapper;
//    private final UserService userService;
//
//    @Override
//    @Transactional
//    public void proWriteUserReview(UserReviewDTO userReview) {
//        proMapper.proWriteUserReview(userReview);
//        String userId = userReview.getUserId();
//        Long userStarRate = userService.selectUserRate(userId);
//        userService.updateUserRate(userId, userStarRate);
//    }
//
//    @Override
//    @Transactional
//    public void userWriteProReview(ProReviewDTO proReview) {
//        Long proReviewId = userMapper.getReviewSeq();
//        proReview.setProReviewId(proReviewId);
//        userMapper.userWriteProReview(ProReviewVO.toEntity(proReview));
//        Long proId = proReview.getProId();
//        Long proStarRate = proService.selectProRate(proId);
//        proService.updateProRate(proId, proStarRate);
//    }
//}
