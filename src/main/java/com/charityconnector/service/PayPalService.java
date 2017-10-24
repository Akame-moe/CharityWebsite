package com.charityconnector.service;

import com.paypal.api.payments.Payment;

public interface PayPalService {

    void payment(Payment payment);

    void paymentExecute(Payment payment);
}
