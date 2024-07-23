package com.example.fpi.domain.vo.file;

import com.example.fpi.domain.dto.file.UserUploadFileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@ToString
public class UserUploadFileVO {

    private Long userUploadFileId;
    private String userUploadFileRoute;
    private String userUploadFileOriginal;
    private Long userUploadId;

    @Builder
    public UserUploadFileVO(Long userUploadFileId, String userUploadFileRoute, String userUploadFileOriginal, Long userUploadId) {
        this.userUploadFileId = userUploadFileId;
        this.userUploadFileRoute = userUploadFileRoute;
        this.userUploadFileOriginal = userUploadFileOriginal;
        this.userUploadId = userUploadId;
    }

    public static UserUploadFileVO toEntity(UserUploadFileDTO userUploadFileDTO) {
        return UserUploadFileVO.builder().userUploadId(userUploadFileDTO.getUserUploadId())
                .userUploadFileOriginal(userUploadFileDTO.getUserUploadFileOriginal())
                .userUploadFileRoute(userUploadFileDTO.getUserUploadFileRoute())
                .userUploadFileId(userUploadFileDTO.getUserUploadFileId())
                .build();
    }

}
