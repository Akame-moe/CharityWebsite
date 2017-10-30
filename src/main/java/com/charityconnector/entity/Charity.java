package com.charityconnector.entity;

import javax.persistence.*;

@Entity
@Table(name = "charity")
public class Charity {


    private Long id;
    private String name;
    private String description;
    private String logoFile;
    private String emailAddress;
    private String paypalAccount;
    private long causes;
    private long nations;

    /* Required by JPA specification */
    public Charity() {
        super();
    }

    public Charity(String desciption, Long id) {
        this.description = desciption;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "logo_file")
    public String getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }

    @Column(name = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Column(name = "paypal_account")
    public String getPaypalAccount() {
        return paypalAccount;
    }

    public void setPaypalAccount(String paypalAccount) {
        this.paypalAccount = paypalAccount;
    }

    @Column(name = "causes")
    public long getCauses() {
        return causes;
    }

    public void setCauses(long causes) {
        this.causes = causes;
    }

    @Column(name = "nations")
    public long getNations() {
        return nations;
    }

    public void setNations(long nations) {
        this.nations = nations;
    }

}


