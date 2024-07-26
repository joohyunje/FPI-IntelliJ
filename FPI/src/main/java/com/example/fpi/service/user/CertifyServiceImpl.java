package com.example.fpi.service.user;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CardInfoFileDTO;
import com.example.fpi.domain.dto.certify.CareerInfoDTO;
import com.example.fpi.domain.dto.certify.CertifyDTO;
import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.pro.ProDTO;

import com.example.fpi.domain.vo.certify.CardInfoFileVO;
import com.example.fpi.domain.vo.certify.CardInfoVO;
import com.example.fpi.domain.vo.certify.CareerInfoVO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.pro.ProVO;

import com.example.fpi.mapper.main.FormMapper;
import com.example.fpi.mapper.user.CertifyMapper;

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
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CertifyServiceImpl implements CertifyService {
    private final CertifyMapper certifyMapper;
    private final FormMapper formMapper;

    // 전문가 테이블에 추가되는 정보
    @Override
    public void addPro(Long proId,String proName, String phoneNumber, String proImg, Long locationId,String userId) {
        ProDTO proDTO = new ProDTO();

        proDTO.setProId(proId);
        proDTO.setProName(proName);
        proDTO.setPhoneNumber(phoneNumber);
        proDTO.setProImg(proImg);
        proDTO.setLocationId(locationId);
        proDTO.setUserId(userId);

        ProVO proVO = ProVO.toEntity(proDTO);
        certifyMapper.addPro(proVO);
    }

//    자격증 발급기관, 자격증번호 정보테이블
    @Override
    public void addCardInfo(Long cardInfoId,Long proId,String certiOrgan, String certiNum) {
        CardInfoDTO cardInfoDTO = new CardInfoDTO();
        cardInfoDTO.setProId(proId);

        cardInfoDTO.setCardInfoId(cardInfoId);
        cardInfoDTO.setCertiOrgan(certiOrgan);
        cardInfoDTO.setCertiNum(certiNum);
        CardInfoVO vo = CardInfoVO.toEntity(cardInfoDTO);
        certifyMapper.addCardInfo(vo);


//        System.out.println(vo);
    }

//    경력작성
    @Override
    public void addCareerInfo(Long careerInfoId, String award, Long proId) {
        CareerInfoDTO careerInfoDTO = new CareerInfoDTO();

        careerInfoDTO.setCareerInfoId(careerInfoId);
        careerInfoDTO.setAward(award);
        careerInfoDTO.setProId(proId);

        certifyMapper.addCareerInfo(CareerInfoVO.toEntity(careerInfoDTO));
    }

// 카테고리 선택
    @Override
    public void insertProCategory(Long categoryId,Long proId) {
        Long nextval = formMapper.getCaSeq();
        CategoryListDTO categoryListDTO = new CategoryListDTO();

        categoryListDTO.setCategoryListId(nextval);
        categoryListDTO.setCategoryId(categoryId);
        categoryListDTO.setProId(proId);
        System.out.println(categoryListDTO);
        CategoryListVO vo= CategoryListVO.toEntity(categoryListDTO);
        certifyMapper.insertProCategory(vo);

    }



//  최종적으로 전문가 인증되는곳
    @Override
    @Transactional
    public void addCertify(CertifyDTO dto,List<MultipartFile> files, MultipartFile proProfile,String certiOrgan,String certiNum,String award) throws IOException {
        Long proId = certifyMapper.getProSeq();
        String userId = dto.getUserId();
        Long cardInfoId = 0L;
        Long careerInfoId = 0L;

//        js로 추가되어 넘어가는 값들이 컬럼에,로 같이 저장되어 분리해서 저장하기 위해 필요
        // 정규표현식 패턴 설정
        Pattern pattern = Pattern.compile("\\s*,\\s*");

        // 패턴을 이용해 문자열 분리
        String[] organ = pattern.split(certiOrgan);
        String[] num = pattern.split(certiNum);
        String[] proAward = pattern.split(award);



//        proProfile에서 리턴받은 값을 받아와서 proImg로 넣어줌
        dto.setProImg(saveProImage(proProfile));

//        전문가 테이블로 전달
        addPro(proId,dto.getProName(),dto.getPhoneNumber(), dto.getProImg(), dto.getLocationId(),userId);

//        자격증 번호,발급기관관련 테이블
        for (int i = 0; i < organ.length; i++) {
            cardInfoId = certifyMapper.getInfoSeq();
            addCardInfo(cardInfoId, proId, organ[i], num[i]);
        }
        //경력작성 테이블
        for (int i = 0; i < proAward.length; i++) {
            careerInfoId = certifyMapper.getInfoSeq();
            addCareerInfo(careerInfoId,proAward[i],proId);
        }

//        선택한 카테고리 관리테이블
        insertProCategory(dto.getCategoryId(),proId);

//        자격증사진
        saveCertifyImage(cardInfoId,files);

//        전문가 인증값YES
        certifyMapper.updateApproval(userId);
    }

//   전문가 프로필사진관리
    @Override
    public String saveProImage(MultipartFile proProfile) throws IOException {


        // 현재 날짜를 기반으로 폴더 경로 생성
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);

        String originalFileName = proProfile.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;

        // 파일 저장 경로 설정 (프로젝트의 정적 자원 경로에 저장)
//            게시판 관리시 datePath까지는 동일
        Path directoryPath = Paths.get("src/main/resources/static/uploads/proImg/" + datePath );
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

//    자격증 사진관리
    @Override
    public void saveCertifyImage(Long cardInfoId,List<MultipartFile> files) throws IOException {
        // 현재 날짜를 기반으로 폴더 경로 생성
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);
        for (MultipartFile file : files) {
            // 방어코드(있어도되고 없어도됨,혹시 모르니!!)
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename();//내가 정한 파일이름
            //uuid : 중복되지않는 값을 만드는 패턴,
            // randomUUID().toString(): uuid에서 랜덤한 값을 가져와서 toString()으로 바꿈
            //replaceAll:가져온값중 -을 없앰, _ + 내가 정한 파일이름
            String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;

            try {
                // 파일 저장 경로 설정
                Path directoryPath = Paths.get("src/main/resources/static/uploads/certifyFile/" + datePath);
                if (!Files.exists(directoryPath)) {//위에  경로안에 중복폴더가 존재하지 않는다면
                    Files.createDirectories(directoryPath); // 폴더가 없으면 생성
                }
                Path filePath = directoryPath.resolve(storedFileName); //파일경로를 정해줌(directoryPath경로 +storedFileName)
                // 파일 저장(copy) files에 getInputStream()을 써서 filePath이름의 경로 저장
                Files.copy(file.getInputStream(), filePath);
// 여기 위에까지는 내 서버에 저장되는 코드
//                사용하려면 db에 저장되있어야함,db에 저장되어야 데이터를 꺼내올수있음
                CardInfoFileDTO fileDTO = new CardInfoFileDTO(); //fileDTO만듬,vo는 setter가 없어서 dto로 만들어줌
                Long cardInfoFileId = certifyMapper.getFileSeq();
                String cardInfoFileRoute =  "/uploads/certifyFile/" + datePath + "/" + storedFileName;
                fileDTO.setCardInfoFileId(cardInfoFileId);
                fileDTO.setCardInfoId(cardInfoId);
                fileDTO.setCardInfoFileOriginal(originalFileName);
                fileDTO.setCardInfoFileRoute(cardInfoFileRoute);
                //directoryPath 와 storedFileName가 /로 연결되어야 해당경로로 잘 찾아들어가서 파일다운로드가 제대로 이루어짐


                certifyMapper.addCardInfoFile(CardInfoFileVO.toEntity(fileDTO)); // 파일 정보 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }




}
