package com.example.fpi.mapper.user;

import com.example.fpi.domain.vo.certify.CardInfoFileVO;
import com.example.fpi.domain.vo.certify.CardInfoVO;
import com.example.fpi.domain.vo.certify.CareerInfoVO;
import com.example.fpi.domain.vo.pro.ProVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CertifyMapper {
    void addPro(ProVO vo);
    void addCardInfo(CardInfoVO vo);
    void addCardInfoFile(CardInfoFileVO vo);
    void addCareerInfo(CareerInfoVO vo);
}
