package com.rsg.upi.payment.util;

public class Constants {

    public static final String UPI_CONTROLLER = "/api/upi";

    public static final String INITIATE_PAY = "/initiate";

    public static final String UPDATE_STATUS = "/updatestatus";

    public static final String KAFKA_TOPIC_PAYMENT_STATUS="upi-payment-status";

    public static final String KAFKA_TOPIC_PAYMENT="upi-payments";

    public static final String INVALID_TRANSACTIONID = "Invalid TransactionId";
}
