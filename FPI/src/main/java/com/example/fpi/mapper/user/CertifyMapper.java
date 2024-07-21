package com.example.fpi.mapper.user;

import com.example.fpi.domain.dto.certify.CertifyDTO;
import com.example.fpi.domain.vo.certify.CardInfoFileVO;
import com.example.fpi.domain.vo.certify.CardInfoVO;
import com.example.fpi.domain.vo.certify.CareerInfoVO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.pro.ProVO;
import com.example.fpi.domain.vo.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CertifyMapper {
//   입력항목마다 관리하는 테이블이 다름
    void addPro(ProVO vo); //proId,phoneNumber,proImg,locationId
    void addCardInfo(CardInfoVO vo); //자격증 발급기관,자격증번호
    void addCardInfoFile(CardInfoFileVO vo); //자격증 사진
    void addCareerInfo(CareerInfoVO vo); //경력 내용
    void insertProCategory(CategoryListVO vo);
    void updateApproval(String userId); //전문가 인증여부


//    CertifyDTO selectCertify(String userId);
    Long getProSeq();
    Long getInfoSeq();
    Long getFileSeq();

}
