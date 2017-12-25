package com.charityconnector.service;

import com.charityconnector.entity.Activity;

public interface ActivityService {

    Activity findById(Long id);

    void deleteById(Long id);

    Activity addActivity(Activity activity);

    void updateSelective(Activity activity);

    void updateDirect(Activity activity);

    Activity[] findArticlesByCharityId(Long charityId);

    int volunteer(Long id, Long donorId);
}
