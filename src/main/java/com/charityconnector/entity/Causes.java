package com.charityconnector.entity;

import javax.persistence.*;



@Entity
@Table(name = "causes")
public class Causes {
    private Long causeId;
    private Long charityId;

    /* Required by JPA specification */
    public Causes() {
        super();
    }

    public Causes(Long causeId, Long charityId) {
        this.causeId = causeId;
        this.charityId = charityId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cause_id")
    public Long getCauseId() {
        return causeId;
    }
    public void setCauseId(Long id) {
        this.causeId = causeId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "charity_id")
    public Long getCharityId() {
        return charityId;
    }
    public void setCharityId(Long charityId) {
        this.charityId = charityId;
    }
}