package com.example.fpi.domain.vo.main;

import com.example.fpi.domain.dto.main.CategoryListDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class CategoryListVO {
    private Long categoryListId;
    private Long categoryId;
    private String userId;
    private Long ProId;

    @Builder
    public CategoryListVO(Long categoryListId, Long categoryId, String userId, Long ProId) {
        this.categoryListId = categoryListId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.ProId = ProId;
    }

    public static CategoryListVO toEntity(CategoryListDTO categoryDTO){
        return CategoryListVO.builder().categoryListId(categoryDTO.getCategoryListId())
                .categoryId(categoryDTO.getCategoryId())
                .userId(categoryDTO.getUserId())
                .ProId(categoryDTO.getProId())
                .build();
    }
}
