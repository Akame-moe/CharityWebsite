package com.charityconnector.entity;

import javax.persistence.*;


@Entity
@Table(name = "donor")
public class Donor {
    private Long id;

    /* Required by JPA specification */
    public Donor() {
        super();
    }

    public Donor(Long id) {
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
}
