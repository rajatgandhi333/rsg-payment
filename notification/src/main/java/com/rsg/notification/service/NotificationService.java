package com.rsg.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    /**
     * To simulate an event of payment
     */

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendSuccessNotification(String paymentDetails){
        log.info("UPI Payment Successful "+paymentDetails);
    }

    public void sendFailureNotification(String paymentDetails){
        log.info("UPI Payment Failed "+paymentDetails);
    }

    public void sendUnknownNotification(String paymentDetails){
        log.info("UPI Payment Not Certain "+paymentDetails);
    }
}
