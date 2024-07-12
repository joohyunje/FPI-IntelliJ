
package com.example.fpi.service.main;

import com.example.fpi.domain.dto.main.LocationDTO;
import com.example.fpi.domain.dto.user.CategoryDTO;
import com.example.fpi.domain.vo.main.CategoryListVO;
import com.example.fpi.domain.vo.main.LocationVO;
import com.example.fpi.domain.vo.user.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FormService {
//    지역아이디 조회
    Long selectLocation(String region, String city);

//    카테고리 리스트 테이블추가
    void insertCategoryList(Long categoryId,String userId);
}
