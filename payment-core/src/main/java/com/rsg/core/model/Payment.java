package com.rsg.core.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment {
    private Long userId;
    private String paymentMethod;
    private Double amount;
    private String status;
    private LocalDateTime timeStamp;
}
