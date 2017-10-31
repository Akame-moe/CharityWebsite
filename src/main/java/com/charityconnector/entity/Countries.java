package com.charityconnector.entity;

import javax.persistence.*;



@Entity
@Table(name = "countries")
public class Countries {
    private Long countryId;
    private Long charityId;
    private Long id;

    /* Required by JPA specification */
    public Countries() {
        super();
    }

    public Countries(Long countryId, Long charityId, Long id) {
        this.countryId = countryId;
        this.charityId = charityId;
        this.id = id;
    }



    @Column(name = "country_id")
    public Long getCountryId() {
        return countryId;
    }
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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