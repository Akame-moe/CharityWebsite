package com.charityconnector.service;

import com.charityconnector.entity.Charity;
import org.springframework.data.domain.Pageable;

public interface CharityService {

    Charity getCharity();

    Charity addCharity(Charity charity);

    Charity[] findByName(String name);

    // This method will only update the NOT NULL field of the charity object.
    void updateSelective(Charity charity);

    // This method will update the charity directly using the default JPA save method.
    void updateDirect(Charity charity);

    void deleteById(Long id);

    Charity findById(Long id);


    Charity[] getPaged(Pageable pageable);
}
