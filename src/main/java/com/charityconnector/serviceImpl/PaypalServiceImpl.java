package com.charityconnector.serviceImpl;

import com.charityconnector.dao.PaypalRepository;
import com.charityconnector.entity.Transaction;
import com.charityconnector.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class PaypalServiceImpl implements PaypalService {

    @Autowired
    PaypalRepository paypalRepository;

    @Autowired
    public PaypalServiceImpl(PaypalRepository paypalRepository) {
        this.paypalRepository = paypalRepository;
    }

    @Override
    public Transaction addPaypal(Transaction transaction) {
        Date now = new Date();
        transaction.setDate(now);
        return paypalRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        paypalRepository.delete(id);
    }

    @Override
    public Transaction findById(Long id) {
        return paypalRepository.findOne(id);
    }

    @Override
    public void updateSelective(Transaction transaction) {
        Transaction readyToUpdate = new Transaction();
        Transaction origin = paypalRepository.findOne(transaction.getId());

        if (transaction.getId() == null) {
            return;
        } else {
            readyToUpdate.setId(transaction.getId());

            Date date = transaction.getDate() == null ? origin.getDate() : transaction.getDate();
            readyToUpdate.setDate(date);

            int amount = transaction.getAmount() <= 0 ? origin.getAmount() : transaction.getAmount();
            readyToUpdate.setAmount(amount);

            long id = transaction.getCharityId() <= 0 ? origin.getCharityId() : transaction.getCharityId();
            readyToUpdate.setCharityId(id);
        }
        paypalRepository.save(readyToUpdate);
    }
}
