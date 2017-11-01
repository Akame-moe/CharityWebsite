package com.charityconnector.dao;

import com.charityconnector.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaypalRepository extends JpaRepository<Transaction, Long> {

}
