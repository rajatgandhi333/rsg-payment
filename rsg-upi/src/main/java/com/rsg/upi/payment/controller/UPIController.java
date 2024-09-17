package com.rsg.upi.payment.controller;

import com.rsg.upi.payment.model.Status;
import com.rsg.upi.payment.model.UPIPayment;
import com.rsg.upi.payment.service.UPIService;
import com.rsg.upi.payment.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.UPI_CONTROLLER)
public class UPIController {

    @Autowired
    private UPIService upiService;

    @GetMapping("/")
    public String getMessage() {
        return "Welcome";
    }

    @PostMapping(Constants.INITIATE_PAY)
    public ResponseEntity<UPIPayment> initiatePay(@RequestBody UPIPayment upiPayment) {
        UPIPayment payment = upiService.initiatePayment(upiPayment);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PutMapping(Constants.UPDATE_STATUS)
    public ResponseEntity<UPIPayment> updateTransaction(@RequestParam String transactionId, @RequestParam Status status) {
        UPIPayment payment = upiService.updatePaymentStatus(transactionId, status);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
