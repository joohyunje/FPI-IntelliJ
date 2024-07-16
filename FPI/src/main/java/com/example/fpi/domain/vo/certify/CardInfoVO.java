package com.example.fpi.domain.vo.certify;

import com.example.fpi.domain.dto.certify.CardInfoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class CardInfoVO {
    private Long cardInfoId;
    private String certiOrgan;
    private String certiNum;
    private Long proId;

    @Builder
    public CardInfoVO(Long cardInfoId, String certiOrgan, String certiNum, Long proId) {
        this.cardInfoId = cardInfoId;
        this.certiOrgan = certiOrgan;
        this.certiNum = certiNum;
        this.proId = proId;
    }

    public static CardInfoVO toEntity(CardInfoDTO cardInfoDTO) {
        return CardInfoVO.builder().cardInfoId(cardInfoDTO.getCardInfoId())
                .certiOrgan(cardInfoDTO.getCertiOrgan())
                .certiNum(cardInfoDTO.getCertiNum())
                .proId(cardInfoDTO.getProId())
        .build();
    }
}
