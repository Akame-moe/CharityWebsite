package com.charityconnector.service;

import com.charityconnector.bean.Charity;

public interface CharityService {

    Charity getCharity();

    Charity addCharity(Charity charity);

    Charity[] findByName(String name);

    void updateSelective(Charity charity);

    void updateDirect(Charity charity);

    void deleteById(Long id);

    Charity findById(Long id);
}
