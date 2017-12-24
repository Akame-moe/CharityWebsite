package com.charityconnector.entity;

import javax.persistence.*;

@Entity
@Table(name = "uk_record_charities")
public class UKRecordCharities {
    private String email;

    public UKRecordCharities(String email){
        this.email = email;
    }

    public UKRecordCharities() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
