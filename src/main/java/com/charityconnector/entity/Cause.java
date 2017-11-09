package com.charityconnector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "cause")
public class Cause{
    private Long id;
    private String name;
    private Set<Charity> charities;

    /* Required by JPA specification */
    public Cause() {
        super();
    }

    public Cause(Long id, String name, Set<Charity> charities) {
        this.id = id;
        this.name = name;
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
    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = name;
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



