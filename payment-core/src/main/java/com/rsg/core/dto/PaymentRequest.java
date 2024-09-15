package com.rsg.core.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long userId;

    private Double amount;

    private String paymentMethod;
}
