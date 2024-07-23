package com.example.fpi.domain.vo.file;


import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@ToString
public class ProUploadFileVO {

    private Long proUploadFileId;
    private String proUploadFileRoute;
    private String proUploadFileOriginal;
    private Long proUploadId;

    @Builder
    public ProUploadFileVO(Long proUploadFileId, String proUploadFileRoute, String proUploadFileOriginal, Long proUploadId) {
        this.proUploadFileId = proUploadFileId;
        this.proUploadFileRoute = proUploadFileRoute;
        this.proUploadFileOriginal = proUploadFileOriginal;
        this.proUploadId = proUploadId;
    }

    public static ProUploadFileVO toEntity(ProUploadFileDTO proUploadFileDTO) {
        return ProUploadFileVO.builder().proUploadFileId(proUploadFileDTO.getProUploadFileId())
                .proUploadFileRoute(proUploadFileDTO.getProUploadFileRoute())
                .proUploadFileOriginal(proUploadFileDTO.getProUploadFileOriginal())
                .proUploadId(proUploadFileDTO.getProUploadId())
                .build();
    }

}
