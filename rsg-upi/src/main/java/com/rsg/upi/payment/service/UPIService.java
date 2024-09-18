package com.rsg.upi.payment.service;

import com.rsg.upi.payment.model.Status;
import com.rsg.upi.payment.model.UPIPayment;
import com.rsg.upi.payment.repository.UPIRepository;
import com.rsg.upi.payment.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.rsg.upi.payment.util.Constants.KAFKA_TOPIC_PAYMENT;

@Service
public class UPIService {

    private static final Logger log = LoggerFactory.getLogger(UPIService.class);
    @Autowired
    private UPIRepository upiRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public UPIPayment initiatePayment(UPIPayment upiPayment) {
        upiPayment.setStatus(Status.PENDING);
        String message = String.format("Payment of Rs.%.2f initiated for UPI ID: %s", upiPayment.getAmount(), upiPayment.getUpiId());
        isValidPayment(message); // Publish event to Kafka
        log.info("Payment Initiated");
        return upiRepository.save(upiPayment);
    }

    public UPIPayment updatePaymentStatus(String transactionId, Status status) {
        Optional<UPIPayment> upiPaymentOptional = Optional.of(upiRepository.findByTransactionId(transactionId));
        if (upiPaymentOptional.isPresent()) {
            UPIPayment upiPayment = upiPaymentOptional.get();
            upiPayment.setStatus(status);
            return upiRepository.save(upiPayment);
        } else {
            throw new RuntimeException(Constants.INVALID_TRANSACTIONID);
        }
    }


    private void isValidPayment(String message) {
        if (simulatePaymentProcessing()) {
            kafkaTemplate.send(KAFKA_TOPIC_PAYMENT, "Payment Successful " + message); //
        } else {
            kafkaTemplate.send(KAFKA_TOPIC_PAYMENT, "Payment Failed " + message); //
        }
    }


    //for simulation
    private boolean simulatePaymentProcessing() {
        return Math.random() > 0.5;
    }
}
