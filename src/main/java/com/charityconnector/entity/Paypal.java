package com.charityconnector.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "paypal")
public class Paypal {
    private Long id;
    private Long charityId;
    private int amount;
    private Date date;
    private String transactionId;


    /* Required by JPA specification */
    public Paypal() {
        super();
    }

    public Paypal(Long id, Long charityId, int amount, Date date, String transactionId) {
        this.id = id;
        this.charityId = charityId;
        this.amount = amount;
        this.date = new Date();
        this.transactionId = transactionId;
    }

    public Paypal(long l) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="charity_id")
    public long getCharityId() {
        return charityId;
    }

    public void setCharityId(long charityId) {
        this.charityId = charityId;
    }

    @Column(name="amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Column(name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }




    @Column(name="transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


}


