package com.charityconnector.entity;

import javax.persistence.*;



@Entity
@Table(name = "cause")
public class Cause{
    private Long id;
    private String causeValue;

    /* Required by JPA specification */
    public Cause() {
        super();
    }

    public Cause(Long id, String causeValue) {
        this.id = id;
        this.causeValue = causeValue;
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

    @Column(name = "cause_value")
    public String getCauseValue() {
        return causeValue;
    }

    public void setCauseValue(String title) {
        this.causeValue = causeValue;
    }

}



