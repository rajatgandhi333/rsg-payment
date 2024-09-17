package com.rsg.upi.payment.kafka;

import com.rsg.upi.payment.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UPIPaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String messgae) {
        kafkaTemplate.send(Constants.KAFKA_TOPIC_PAYMENT_STATUS, messgae);
    }
}
