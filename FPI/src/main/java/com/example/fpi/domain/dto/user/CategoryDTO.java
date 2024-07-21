package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CategoryDTO {
    private Long categoryListId;
    private Long categoryId;
    private String userId;
    private Long proId;


}
