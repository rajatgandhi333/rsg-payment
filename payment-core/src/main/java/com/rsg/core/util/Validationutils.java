package com.rsg.core.util;

import com.rsg.core.exceptions.PaymentException;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class Validationutils {
    /**
     * Used for common validations
     */

    public void validateUser(Long userId) {
        if (userId == null || userId <= 0) throw new PaymentException("Invalid UserId: " + userId);
    }

    public void validatePaymentAmount(Double amount) {
        if (amount == null || amount <= 0) throw new PaymentException("Invalid Payment amoun: " + amount);
    }

    public void validatePaymentMethod(String paymentMethod) {
        if (StringUtils.isBlank(paymentMethod) ||
                (!paymentMethod.equalsIgnoreCase("UPI") && !paymentMethod.equalsIgnoreCase("CREDIT_CARD"))) {
            throw new PaymentException("Invalid payment method: " + paymentMethod);
        }
    }

    public void validateStringNotBlank(String value, String field) {
        if (StringUtils.isBlank(value)) {
            throw new PaymentException(field + " Cannot be blank");
        }
    }


}
