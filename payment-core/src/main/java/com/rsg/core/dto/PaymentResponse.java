package com.rsg.core.dto;

import lombok.Data;

@Data
public class PaymentResponse {

    private Long transactionId;

    private String status;

    private String message;
}
