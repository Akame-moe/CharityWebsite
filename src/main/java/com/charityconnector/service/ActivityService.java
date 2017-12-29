package com.charityconnector.service;

import com.charityconnector.entity.Activity;
import com.charityconnector.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ActivityService {

    Activity findById(Long id);

    void deleteById(Long id);

    Activity addActivity(Activity activity);

    void updateSelective(Activity activity);

    void updateDirect(Activity activity);

    Activity[] findArticlesByCharityId(Long charityId);

    int volunteer(Long id, Long donorId);

    Page<Activity> findByHoldDateAndCountry(Date holdDateFrom, Date holdDateTo, Country country, Pageable pageable);
}
