package com.charityconnector.service;

import com.charityconnector.entity.Transaction;

public interface PaypalService {

    Transaction addPaypal(Transaction transaction);

    void deleteById(Long id);

    Transaction findById(Long id);

    void updateSelective(Transaction transaction);
}
