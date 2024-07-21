package com.example.fpi.service.main;

import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.main.LocationDTO;
import com.example.fpi.domain.dto.user.CategoryDTO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.main.LocationVO;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.main.FormMapper;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormMapper formMapper;

//    지역아이디 조회하여 없으면 지역테이블에 데이터 추가 후 지역아이디 가져옴
    @Override
    public Long selectLocation(String region, String city) {
//        해당값이 지역테이블에 있는지 검사
        Long locationId = formMapper.selectLocation(region, city);
//        없다면 추가해줘야함
        if (locationId == null || locationId == 0) {
            Long nextval = formMapper.getLoSeq(); // 데이터베이스에서 시퀀스 값을 가져옴

            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocationId(nextval);  // 시퀀스 값 설정
            locationDTO.setRegion(region);
            locationDTO.setCity(city);
            LocationVO locationVO = LocationVO.toEntity(locationDTO);
            formMapper.insertLocation(locationVO);

            System.out.println(nextval);
            return nextval;



        }

        return locationId;


    }
// 유저가 선택한 카테고리를 카테고리리스트테이블에 정보 입력
    @Override
    public void insertCategoryList(Long categoryId,String userId) {
        Long nextval = formMapper.getCaSeq();
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        categoryListDTO.setCategoryListId(nextval);
        categoryListDTO.setCategoryId(categoryId);
        categoryListDTO.setUserId(userId);
        CategoryListVO vo= CategoryListVO.toEntity(categoryListDTO);
        formMapper.insertCategoryList(vo);
    }

}
