package com.charityconnector.entity;

import javax.persistence.*;

public class DonorTrans {

    private String transactionId;
    private Long donorId;

    public DonorTrans() {
        super();
    }

    public DonorTrans(String transactionId, Long donorId) {
        this.transactionId = transactionId;
        this.donorId = donorId;
    }

    @Column(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "donor_id")
    public Long getDonorId() {
        return donorId;
    }
    public void setTransactionId(Long donorId) {
        this.donorId = donorId;
    }

}
