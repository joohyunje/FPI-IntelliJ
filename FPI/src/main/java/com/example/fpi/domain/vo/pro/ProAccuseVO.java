package com.example.fpi.domain.vo.pro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@ToString
public class ProAccuseVO {

    private Long userAccuseId;
    private String userAccuseContent;
    private String userId;
    private Long proId;

}
