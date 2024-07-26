package com.example.fpi.service.user;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CertifyDTO;
import com.example.fpi.domain.vo.certify.CardInfoFileVO;
import com.example.fpi.domain.vo.certify.CardInfoVO;
import com.example.fpi.domain.vo.certify.CareerInfoVO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.pro.ProVO;
import com.example.fpi.domain.vo.user.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface CertifyService {
    void addPro(Long proId,String proName,String phoneNumber,String proImg,Long locationId,String userId); //proId,phoneNumber,proImg,locationId
    void addCardInfo(Long cardInfoId,Long proId,String certiOrgan,String certiNum); //자격증 발급기관,자격증번호

    void addCareerInfo(Long careerInfoId,String award,Long proId); //경력 내용
    void insertProCategory(Long categoryId,Long proId);


    void addCertify(CertifyDTO dto,List<MultipartFile> certifyFiles, MultipartFile proProfile,String certiOrgan,String certiNum,String award) throws IOException; //최종종합
    String saveProImage(MultipartFile proProfile) throws IOException;  //전문가 활동사진 파일
    void saveCertifyImage(Long cardInfoId,List<MultipartFile> certifyFiles) throws IOException; //자격증 사진


}
