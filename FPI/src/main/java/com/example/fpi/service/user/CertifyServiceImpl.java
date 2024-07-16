package com.example.fpi.service.user;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import com.example.fpi.domain.dto.certify.CardInfoFileDTO;
import com.example.fpi.domain.dto.certify.CareerInfoDTO;
import com.example.fpi.domain.dto.certify.CertifyDTO;
import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.pro.ProDTO;
import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.vo.certify.CardInfoFileVO;
import com.example.fpi.domain.vo.certify.CardInfoVO;
import com.example.fpi.domain.vo.certify.CareerInfoVO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.pro.ProVO;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.user.CertifyMapper;
import com.example.fpi.service.main.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertifyServiceImpl implements CertifyService {
    private final CertifyMapper certifyMapper;
    private final ProVO proVO;
    private final ProDTO proDTO;
    private final FormService formService;


    @Override
    public void addCertify(CertifyDTO dto,List<MultipartFile> files) {
        Long proNextval = certifyMapper.getProSeq();
        Long infoNextval = certifyMapper.getInfoSeq(); //파일관리 테이블에 넣어줘야하기때문에 필요

//        전문가 테이블에 저장할 값들, proId,phoneNumber,proImg,locationId
        ProDTO proDTO = new ProDTO();
        proDTO.setProId(dto.getProId());
        proDTO.setProName(dto.getProName());
        proDTO.setProImg(dto.getProImg());
        proDTO.setLocationId(formService.selectLocation(dto.getRegion(),dto.getCity())); //지역select값 가져오기
        certifyMapper.addPro(ProVO.toEntity(proDTO));

//        자격증 테이블에 저장할값들, 발급기관,자격증번호
        CardInfoDTO cardInfoDTO = new CardInfoDTO();
        cardInfoDTO.setCertiOrgan(dto.getCertiOrgan());
        cardInfoDTO.setCertiNum(dto.getCertiNum());
        cardInfoDTO.setProId(proNextval);
        certifyMapper.addCardInfo(CardInfoVO.toEntity(cardInfoDTO));

//        자격증 사진테이블에 저장할 값들
        CardInfoFileDTO cardInfoFileDTO = new CardInfoFileDTO();
        cardInfoFileDTO.setCardInfoFileRoute(dto.getCardinfoFileRoute());
        cardInfoFileDTO.setCardInfoFileOriginal(dto.getCardinfoFileOriginal());
        cardInfoFileDTO.setCardFileSaved(dto.getCardinfoFileSaved());
        cardInfoFileDTO.setCardInfoId(infoNextval);
        certifyMapper.addCardInfoFile(CardInfoFileVO.toEntity(cardInfoFileDTO));

//         경력 테이블
        CareerInfoDTO careerInfoDTO = new CareerInfoDTO();
        careerInfoDTO.setProId(proNextval);
        careerInfoDTO.setAward(dto.getAward());
        certifyMapper.addCareerInfo(CareerInfoVO.toEntity(careerInfoDTO));

//        선택된 카테고리 관리
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        categoryListDTO.setProId(proNextval);
        categoryListDTO.setCategoryId(dto.getCategoryId());
        certifyMapper.insertProCategory(CategoryListVO.toEntity(categoryListDTO));

//        전문가 인증값YES
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(dto.getUserId());
        certifyMapper.updateApproval(UserVO.toEntity(userDTO));




    }

    @Override
    public CertifyDTO selectCertify(String userId) {
        return certifyMapper.selectCertify(userId);
    }
}
