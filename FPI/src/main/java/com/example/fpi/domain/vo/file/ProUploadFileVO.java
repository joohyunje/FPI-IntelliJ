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
    private String proUploadFileSaved;
    private Long proUploadId;

    @Builder
    public ProUploadFileVO(Long proUploadFileId, String proUploadFileRoute, String proUploadFileOriginal, String proUploadFileSaved, Long proUploadId) {
        this.proUploadFileId = proUploadFileId;
        this.proUploadFileRoute = proUploadFileRoute;
        this.proUploadFileOriginal = proUploadFileOriginal;
        this.proUploadFileSaved = proUploadFileSaved;
        this.proUploadId = proUploadId;
    }

    public static ProUploadFileVO toEntity(ProUploadFileDTO proUploadFileDTO){
        return ProUploadFileVO.builder().proUploadFileId(proUploadFileDTO.getProUploadFileId())
                .proUploadFileRoute(proUploadFileDTO.getProUploadFileRoute())
                .proUploadFileOriginal(proUploadFileDTO.getProUploadFileOriginal())
                .proUploadFileSaved(proUploadFileDTO.getProUploadFileSaved())
                .proUploadId(proUploadFileDTO.getProUploadId())
                .build();
    }

}
