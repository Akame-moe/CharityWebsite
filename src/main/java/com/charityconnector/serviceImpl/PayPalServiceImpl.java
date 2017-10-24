package com.charityconnector.serviceImpl;

import com.charityconnector.service.PayPalService;
import com.paypal.api.payments.Payment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class PayPalServiceImpl implements PayPalService {
    private final RestTemplate restTemplate;

    public PayPalServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void payment(Payment payment) {

    }

    @Override
    public void paymentExecute(Payment payment) {

    }
}
