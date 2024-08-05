package com.example.fpi.service.pro;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CardInfoFileDTO;
import com.example.fpi.domain.dto.certify.CareerInfoDTO;
import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.pro.*;
import com.example.fpi.domain.dto.user.UserAccuseDTO;
import com.example.fpi.domain.dto.user.UserReviewDTO;
import com.example.fpi.domain.util.PagedResponse;
import com.example.fpi.domain.vo.certify.CardInfoVO;
import com.example.fpi.domain.vo.certify.CareerInfoVO;
import com.example.fpi.domain.vo.file.ProUploadFileVO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.pro.ProVO;
import com.example.fpi.domain.vo.user.UserAccuseVO;
import com.example.fpi.mapper.File.FileMapper;
import com.example.fpi.mapper.pro.ProMapper;
import com.example.fpi.mapper.user.CertifyMapper;
import com.example.fpi.service.user.CertifyService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ProServiceImpl implements ProService {

    private final ProMapper proMapper;
    private final CertifyService certifyService;
    private final FileMapper fileMapper;
    private final CertifyMapper certifyMapper;

    //    받은 요청 목록
    @Override
    public PagedResponse<ProReceivedReqListDTO> selectReceivedReq(Long proId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countReceivedRequest(proId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);

        List<ProReceivedReqListDTO> requests = proMapper.selectReceivedReq(proId, startRow, endRow, sort);

        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }

    //  보낸 요청 목록
    @Override
    public PagedResponse<ProSendReqListDTO> selectSendReq(Long proId, int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalRequest = proMapper.countSendRequest(proId);
        int totalPages = (int) Math.ceil((double) totalRequest / pageSize);

        List<ProSendReqListDTO> requests = proMapper.selectSendReq(proId, startRow, endRow, sort);

        return new PagedResponse<>(requests, page, totalPages, pageSize, totalRequest);
    }

    @Override
    public ProRequestDetailDTO selectProReqDetail(Long proRequestId) {
        return proMapper.selectProReqDetail(proRequestId);
    }

    @Override
    public List<ProReviewListDTO> selectProReview(Long proId) {
        return proMapper.selectProReview(proId);
    }

    //전문가전환시 유저아이디통해서 전문가아이디 가져옴
    @Override
    public Long selectProId(String userId) {
        return proMapper.selectProId(userId);
    }


    //    전문가 상세정보 조회
    @Override
    public ProDetailDTO detailPro(Long proId) {

        return proMapper.detailPro(proId);

    }

    //    자격증 파일 여러개 가져옴
    @Override
    public List<CardInfoFileDTO> selectCardInfoFile(Long proId) {
        return proMapper.cardFileList(proId);
    }

    //    자격증 정보 여러개 가져옴
    @Override
    public List<CardInfoDTO> selectCard(Long proId) {
        return proMapper.selectCard(proId);
    }


    //    전문가 수정하기에 정보 뿌려줌
    @Override
    public ProEditDTO selectEditPro(Long proId) {
        return proMapper.selectEditPro(proId);
    }

    // 전문가 수정정보는 여기서 최종으로 모아서 update됨
    @Override
    @Transactional

    public void updatePro(ProEditDTO dto, List<CardInfoDTO> cards, List<MultipartFile> files, MultipartFile proProfile) throws IOException {
        String proImg = editProImage(dto.getProId(), proProfile);

        editPro(dto.getProName(), dto.getPhoneNumber(), proImg, dto.getLocationId(), dto.getProId());
        editCardInfo(cards);
        editCardInfoFile(dto.getProId(), files);
        editCategory(dto.getCategoryId(), dto.getProId());
        editCareerInfo(dto.getCareerInfoId(), dto.getAward());


    }


//     전문가 테이블 수정

    @Override
    public void editPro(String proName, String phoneNumber, String proImg, Long locationId, Long proId) {
        ProDTO dto = new ProDTO();
        dto.setProName(proName);
        dto.setPhoneNumber(phoneNumber);
        dto.setProImg(proImg);
        dto.setLocationId(locationId);
        dto.setProId(proId);
        proMapper.editPro(ProVO.toEntity(dto));
        System.out.println(dto);
    }

    //  전문가가 선택한 카테고리 수정
    @Override
    public void editCategory(Long categoryId, Long proId) {
        CategoryListDTO dto = new CategoryListDTO();
        dto.setCategoryId(categoryId);
        dto.setProId(proId);
        proMapper.editCategory(CategoryListVO.toEntity(dto));
    }

    // 작성한 자격증 사진은 추가만 가능,전문가인증폼에서 썼던루트 그대로 가져다 씀
    @Override
    public void editCardInfoFile(Long proId, List<MultipartFile> files) throws IOException {
        certifyService.saveCertifyImage(proId, files);

    }

    //    작성한 자격증 정보리스트 수정
    @Override
    public void editCardInfo(List<CardInfoDTO> cards) {
        for (CardInfoDTO dto : cards) {
            if (dto.getCardInfoId() == 0L) {
                //insert
                Long cardInfoId = certifyMapper.getInfoSeq();
                certifyService.addCardInfo(cardInfoId, dto.getProId(), dto.getCertiOrgan(), dto.getCertiNum());
            } else {
                proMapper.editCardInfo(CardInfoVO.toEntity(dto));
            }
        }
    }

    //    작성한 경력사항 수정
    @Override
    public void editCareerInfo(Long careerInfoId, String award) {
        CareerInfoDTO dto = new CareerInfoDTO();

        dto.setCareerInfoId(careerInfoId);
        dto.setAward(award);

        proMapper.editCareerInfo(CareerInfoVO.toEntity(dto));
    }

    //    전문가 정보수정에서 프로필 사진변경
    @Override
    public String editProImage(Long ProId, MultipartFile proProfile) throws IOException {
        String proImg = detailPro(ProId).getProImg();
//        선택값이 없으면 기존꺼 가져옴
        if (proProfile == null || proProfile.isEmpty()) {
            return proImg;
        }
//        기존꺼 삭제하고 새로업데이트
        else {
            Path proImgPath = Paths.get("src/main/resources/static" + proImg);
            if (Files.exists(proImgPath)) {
                Files.delete(proImgPath);
            }
            // 현재 날짜를 기반으로 폴더 경로 생성
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String datePath = now.format(formatter);

            String originalFileName = proProfile.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;

            // 파일 저장 경로 설정 (프로젝트의 정적 자원 경로에 저장)
//            게시판 관리시 datePath까지는 동일
            Path directoryPath = Paths.get("src/main/resources/static/uploads/proImg/" + datePath);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); // 폴더가 없으면 생성
            }
            Path filePath = directoryPath.resolve(storedFileName);
            // 파일 저장
            Files.copy(proProfile.getInputStream(), filePath);

            // 저장된 파일의 URL 반환
            String fileUrl = "/uploads/proImg/" + datePath + "/" + storedFileName;

            return fileUrl;
        }
    }


    //전문가 탈퇴,탈퇴시 서버에 저장된 프로필사진도 함께삭제
    @Override
    public void deletePro(Long proId, String proName) throws IOException {

        Path proImg = Paths.get("src/main/resources/static" + proMapper.selectProInfo(proId).getProImg());

        if (Files.exists(proImg)) {
            Files.delete(proImg);
        }
        proMapper.deletePro(proId, proName);
    }

    //    전문가 탈퇴시 서버에 저장된 자격증사진 삭제
    @Override
    public void deleteCardFile(Long proId) throws IOException {

        List<CardInfoFileDTO> cardInfoFileList = proMapper.cardFileList(proId);
        for (CardInfoFileDTO cardInfoFile : cardInfoFileList) {
            System.out.println(cardInfoFile.getCardInfoFileRoute());
            Path file = Paths.get("src/main/resources/static" + cardInfoFile.getCardInfoFileRoute());
            if (Files.exists(file)) {
                Files.delete(file);
            }
        }
    }


    // 전문가 삭제시 이름비교 위해 필요
    @Override
    public String getProName(Long proId) {
        return proMapper.selectProName(proId);
    }

    //    전문가가 올린 견적 상세보기
    @Override
    public ProUploadDetailDTO selectProUploadDetail(Long proUploadId) {
        return proMapper.selectProUploadDetail(proUploadId);
    }

    //  전문가 견적 작성하기
    @Override
    @Transactional
    public void saveProUpload(ProUploadDTO proUpload, List<MultipartFile> files) {
        Long proUploadId = proMapper.getUploadSeq();
        proUpload.setProUploadId(proUploadId);
        proMapper.saveProUpload(proUpload);

        saveProUploadFile(proUploadId, files);
    }

    @Override
    public void saveProUploadFile(Long proUploadId, List<MultipartFile> files) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);

        for (MultipartFile file : files) {
            // 방어코드
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename();
            String proUploadFileRouteName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;

            try {
                // 파일 저장 경로 설정
                Path directoryPath = Paths.get("src/main/resources/static/uploads/proUpload/" + datePath);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath); // 폴더가 없으면 생성
                }
                Path filePath = directoryPath.resolve(proUploadFileRouteName);
                // 파일 저장
                Files.copy(file.getInputStream(), filePath);

                ProUploadFileDTO proUploadFileDTO = new ProUploadFileDTO();
                proUploadFileDTO.setProUploadId(proUploadId);
                proUploadFileDTO.setProUploadFileOriginal(originalFileName);
                proUploadFileDTO.setProUploadFileRoute("/uploads/proUpload/" + datePath + "/" + proUploadFileRouteName);

                fileMapper.insertProUploadFile(ProUploadFileVO.toEntity(proUploadFileDTO)); // 파일 정보 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ProLocationDTO selectProLocation(Long proId) {
        return proMapper.selectProLocation(proId);
    }

    @Override
    public void proRequest(ProRequestDTO proRequestDTO) {
        proMapper.proRequest(proRequestDTO);
    }

    @Override
    public Long checkProRequest(Long userUploadId, Long proId) {
        return proMapper.checkProRequest(userUploadId, proId);
    }

    @Override
    @Transactional
    public void proWriteUserReview(UserReviewDTO userReview) {
        proMapper.proWriteUserReview(userReview);
        String userId = userReview.getUserId();
        Long userStarRate = selectUserRate(userId);
        updateUserRate(userId, userStarRate);
    }

    @Override
    public void deleteUserRequest(Long userRequestId) {
        proMapper.deleteUserRequest(userRequestId);
    }

    //    올린 견적으로 경력 가져오기
    @Override
    public List<ProCareerInfoListDTO> selectProCareerByUp(Long proUploadId) {
        return proMapper.selectProCareerByUp(proUploadId);
    }

    //  요청으로  전문가 경력 가져오기
    @Override
    public List<ProCareerInfoListDTO> selectProCareerByReq(Long proRequestId) {
        return proMapper.selectProCareerByReq(proRequestId);
    }

    //  전문가 찾기
    @Override
    public PagedResponse<ProUploadListDTO> selectProUploadList(int page, int pageSize, String search, String searchType,String searchSubject) {

        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalUploads = proMapper.countProUpload(search,searchType,searchSubject);
        int totalPages = (int) Math.ceil((double) totalUploads / pageSize);

        List<ProUploadListDTO> uploads = proMapper.selectProUploadList(startRow, endRow, search, searchType,searchSubject);

        return new PagedResponse<>(uploads, page, totalPages, pageSize, totalUploads);
    }


    @Override
    public void updateProAccept(Long userRequestId) {
        proMapper.updateProAccept(userRequestId);
    }

    @Override
    public void updateProComplete(Long userRequestId) {
        proMapper.updateProComplete(userRequestId);
    }

    @Override
    public void proAccuseUser(UserAccuseDTO userAccuseDTO) {
        proMapper.proAccuseUser(UserAccuseVO.toEntity(userAccuseDTO));
    }

    @Override
    public String selectUserIdByUserRequestId(Long userRequestId) {
        return proMapper.selectUserIdByUserRequestId(userRequestId);
    }


    //    전문가 수정하기에서 자격증 수정정보를 List에 담아서 넘겨주기 위해 필요한것
//    길이를 비교하여 insert,update 둘다 사용함
    @Override
    public List<CardInfoDTO> getCardInfoList(Long proId, String cardInfoId, String certiOrgan, String certiNum) {

        List<CardInfoDTO> cardInfoList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\s*,\\s*");

        // 패턴을 이용해 문자열 분리
        String[] cardInfo = pattern.split(cardInfoId);
        String[] organ = pattern.split(certiOrgan);
        String[] num = pattern.split(certiNum);

        System.out.println(cardInfoId);
        System.out.println(cardInfo.length);

//        ex) 3칸 i=3, i=0,1
//        추가된 cardInfoId은 아무정보가 담기지않음,그래서 0을 임의로 담아서 insert,update처리하는곳에 넘겨줌 이 값은 2가됨
        for (int i = 0; i < num.length; i++) {
            CardInfoDTO dto = new CardInfoDTO();
//            추가된 input 없어서 기존 값을 넣어줌
            if (cardInfo.length - 1 >= i) {
                dto.setCardInfoId(Long.parseLong(cardInfo[i]));
            }
//            input이 추가되었음, 0을 담아서 넘겨줄것임 ,editcardInfo에서 insert,update처리하는거 있음
            else {
                dto.setCardInfoId(0L);
            }
            dto.setProId(proId);
            dto.setCertiOrgan(organ[i]);
            dto.setCertiNum(num[i]);
            cardInfoList.add(dto);


        }

        return cardInfoList;
    }

    @Override
    public Long selectProIdByProUploadId(Long proUploadId) {
        return proMapper.selectProIdByProUploadId(proUploadId);
    }


    @Override
    public Long selectUserRate(String userId) {
        return proMapper.selectUserRate(userId);
    }

    @Override
    public void updateUserRate(String userId, Long userStarRate) {
        proMapper.updateUserRate(userId, userStarRate);
    }

    @Override
    public String selectUserIdByProRequestId(Long proRequestId) {
        return proMapper.selectUserIdByProRequestId(proRequestId);
    }

    @Override
    public void updateUserRequestUserReview(Long userRequestId) {
        proMapper.updateUserRequestUserReview(userRequestId);
    }

    @Override
    public void updateProRequestUserReview(Long proRequestId) {
        proMapper.updateProRequestUserReview(proRequestId);
    }

    @Override
    public void updateEmpCnt(Long proId, Long empCnt) {
        proMapper.updateEmpCnt(proId, empCnt);
    }

    @Override
    public Long empCount(Long proId) {
        return proMapper.empCount(proId);
    }

    //   전문가 마이페이지에서 클릭으로 정보삭제
    @Override
    public void clickDeleteCard(Long cardInfoId) {
        proMapper.clickDeleteCard(cardInfoId);
    }

    @Override
    public Long selectProReviewCnt(Long proId) {
        return proMapper.selectProReviewCnt(proId);
    }


}
