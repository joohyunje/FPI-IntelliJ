package com.example.fpi.domain.dto.pay;

import lombok.Data;

@Data
public class CashDTO {

    int userCash;
    int userPlusCash;
    String userId;
    Long couponId;

}
