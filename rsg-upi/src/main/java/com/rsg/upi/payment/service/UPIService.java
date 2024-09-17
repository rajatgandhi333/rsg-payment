package com.rsg.upi.payment.service;

import com.rsg.upi.payment.model.Status;
import com.rsg.upi.payment.model.UPIPayment;
import com.rsg.upi.payment.repository.UPIRepository;
import com.rsg.upi.payment.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UPIService {

    @Autowired
    private UPIRepository upiRepository;

    public UPIPayment initiatePayment(UPIPayment upiPayment) {
        upiPayment.setStatus(Status.PENDING);
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
}
