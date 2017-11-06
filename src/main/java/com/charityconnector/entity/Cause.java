package com.charityconnector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "cause")
public class Cause{
    private Long id;
    private String causeValue;
    private Set<Charity> charities;

    /* Required by JPA specification */
    public Cause() {
        super();
    }

    public Cause(Long id, String causeValue, Set<Charity> charities) {
        this.id = id;
        this.causeValue = causeValue;
        this.charities = charities;
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

    @ManyToMany(mappedBy = "causes")
    @JsonIgnore
    public Set<Charity> getCharities() {
        return charities;
    }

    public void setCharities(Set<Charity> charities) {
        this.charities = charities;
    }
}



