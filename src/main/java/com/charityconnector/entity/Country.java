package com.charityconnector.entity;

import javax.persistence.*;



@Entity
@Table(name = "country")
public class Country {
    private Long id;
    private String countryValue;

    /* Required by JPA specification */
    public Country() {
        super();
    }

    public Country(Long id, String countryValue) {
        this.id = id;
        this.countryValue = countryValue;
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

    @Column(name = "country_value")
    public String getCountryValue() {
        return countryValue;
    }

    public void setCountryValue(String title) {
        this.countryValue = countryValue;
    }

}



