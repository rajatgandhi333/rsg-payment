package com.rsg.core.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private Long userId;
    private Double amount;
    private String status;
    private String paymentMethod;
    private LocalDateTime timeStamp;
}
