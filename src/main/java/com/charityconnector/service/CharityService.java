package com.charityconnector.service;

import com.charityconnector.entity.Charity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface CharityService {

    Charity getCharity();

    Charity addCharity(Charity charity);

    Charity[] findByName(String name);

    Charity findRandom();

    // This method will only update the NOT NULL field of the charity object.
    void updateSelective(Charity charity);

    // This method will update the charity directly using the default JPA save method.
    void updateDirect(Charity charity);

    void deleteById(Long id);

    Charity findById(Long id);

    Charity findByOauthUserId(String oauthUserId);

    Charity[] findPaged(Pageable pageable);

    Page<Charity> findByNameLike(String name, Pageable pageable);

    Set<Charity> getCharitiesByCause(String cause);

    Set<Charity> getCharitiesByCountry(String country);

    Page<Charity> findAll(Pageable pageable);

    List<Charity> findAll(Sort sort);

    Charity thumbUp(Long id);

    int thumbUpUnique(Long charityId, Long donorId);

    int getCharityThumbsUpById(Long id);
}
