package org.interswitch.onboardingservice.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {

    Long productId;
    BigDecimal amount;
    String customerNo;
    Long accountId;
}
