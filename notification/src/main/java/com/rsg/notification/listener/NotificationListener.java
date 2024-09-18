package com.rsg.notification.listener;


import com.rsg.notification.service.NotificationService;
import com.rsg.notification.util.Constant;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class NotificationListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);
    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = Constant.UPI_PAYMENT_TOPIC, groupId = Constant.UPI_PAYMENT_GROUP_ID)
    public void handleUPIPayments(String message) {
        log.info("Received UPI payments " + message);
        if (message.contains("SUCCESS")) {
            notificationService.sendSuccessNotification(message);
        } else if (message.contains("FAILURE")) {
            notificationService.sendFailureNotification(message);
        } else {
            notificationService.sendUnknownNotification(message);
        }
    }
}
