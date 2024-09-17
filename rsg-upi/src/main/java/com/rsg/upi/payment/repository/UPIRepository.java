package com.rsg.upi.payment.repository;

import com.rsg.upi.payment.model.UPIPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UPIRepository extends JpaRepository<UPIPayment, Long> {

    UPIPayment findByTransactionId(String transactionId);
}
