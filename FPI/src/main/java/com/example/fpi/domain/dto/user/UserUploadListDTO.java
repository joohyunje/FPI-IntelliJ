package com.example.fpi.domain.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UserUploadListDTO {

    private Long userUploadId;
    private Long userId;
    private String userUploadTitle;
    private Long userStarRate;
    private LocalDate userUploadDate;
    private String userName;
    private String serviceName;
    private String userImg;

}
