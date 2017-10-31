package com.charityconnector.entity;

import javax.persistence.*;



@Entity
@Table(name = "countries")
public class Countries {
    private Long countryId;
    private Long charityId;

    /* Required by JPA specification */
    public Countries() {
        super();
    }

    public Countries(Long countryId, Long charityId) {
        this.countryId = countryId;
        this.charityId = charityId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    public Long getCountryId() {
        return countryId;
    }
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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