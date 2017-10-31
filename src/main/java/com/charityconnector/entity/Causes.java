package com.charityconnector.entity;

import javax.persistence.*;


@Entity
@Table(name = "causes")
public class Causes {
    private Long causeId;
    private Long charityId;
    private Long id;

    /* Required by JPA specification */
    public Causes() {
        super();
    }

    public Causes(Long causeId, Long charityId,Long id) {
        this.causeId = causeId;
        this.charityId = charityId;
        this.id = id;
    }



    @Column(name = "cause_id")
    public Long getCauseId() {
        return causeId;
    }
    public void setCauseId(Long id) {
        this.causeId = causeId;
    }


    @Column(name = "charity_id")
    public Long getCharityId() {
        return charityId;
    }
    public void setCharityId(Long charityId) {
        this.charityId = charityId;
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
}