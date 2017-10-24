package com.charityconnector.controller;

import com.paypal.api.payments.Payment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "/donate")
public class DonationController {

    @RequestMapping(path = "/payPal", method = RequestMethod.POST)
    public String payPalInitiate(Payment payment) {
        return "";
    }

    @RequestMapping(path = "/payPal/validate", method = RequestMethod.POST)
    public String payPalValidate(Payment payment) {
        return "";
    }
}
