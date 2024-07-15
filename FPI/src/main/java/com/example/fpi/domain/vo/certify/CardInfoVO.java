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
    private String certifyOrgan;
    private String certifyNumber;
    private Long proId;

    @Builder
    public CardInfoVO(Long cardInfoId, String certifyOrgan, String certifyNumber, Long proId) {
        this.cardInfoId = cardInfoId;
        this.certifyOrgan = certifyOrgan;
        this.certifyNumber = certifyNumber;
        this.proId = proId;
    }

    public static CardInfoVO toEntity(CardInfoDTO cardInfoDTO) {
        return CardInfoVO.builder().cardInfoId(cardInfoDTO.getCardInfoId())
                .certifyOrgan(cardInfoDTO.getCertifyOrgan())
                .certifyNumber(cardInfoDTO.getCertifyNumber())
                .proId(cardInfoDTO.getProId())
        .build();
    }
}
