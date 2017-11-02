package com.charityconnector.dao;

import com.charityconnector.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findCountryByCountryValue(String countryValue);

}
