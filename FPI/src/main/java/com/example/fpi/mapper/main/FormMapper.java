package com.example.fpi.mapper.main;

import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.main.LocationVO;
import com.example.fpi.domain.vo.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormMapper {
//    카테고리 리스트테이블에 카테고리테이블에서 조회하여 추가
    void insertCategoryList(CategoryListVO categoryListVO);
    Long getCaSeq();

//    유저테이블에 지역아이디 추가
    Long selectLocation(String region, String city);

//    지역아이디없으면 생성
    void insertLocation(LocationVO locationVO);
    Long getLoSeq();

}
