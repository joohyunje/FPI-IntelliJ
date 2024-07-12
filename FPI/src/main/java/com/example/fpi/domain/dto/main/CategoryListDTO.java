package com.example.fpi.domain.dto.main;

import com.example.fpi.domain.dto.user.CategoryDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
public class CategoryListDTO {
    private Long categoryListId;
    private Long categoryId;
    private String userId;
    private Long ProId;

}
