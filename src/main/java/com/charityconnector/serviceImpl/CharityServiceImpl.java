package com.charityconnector.serviceImpl;

import com.charityconnector.dao.CauseRepository;
import com.charityconnector.dao.CharityRepository;
import com.charityconnector.dao.CountryRepository;
import com.charityconnector.entity.Cause;
import com.charityconnector.entity.Charity;
import com.charityconnector.entity.Country;
import com.charityconnector.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class CharityServiceImpl implements CharityService {

    @Resource
    private CharityRepository charityRepository;

    @Resource
    private CauseRepository causeRepository;

    @Resource
    private CountryRepository countryRepository;

    @Autowired
    public CharityServiceImpl(CharityRepository charityRepository) {
        this.charityRepository = charityRepository;
    }

    @Override
    public Charity getCharity() {
        return charityRepository.findOne(1L);
    }

    @Override
    public Charity addCharity(Charity charity) {
        Charity c = charityRepository.save(charity);
        return c;
    }

    @Override
    public Charity[] findByName(String name) {
        Charity[] charities = charityRepository.findByNameLike(name);
        return charities;
    }

    @Override
    public Charity findRandom() {
        return charityRepository.findRandom();
    }

    @Override
    public void updateSelective(Charity charity) {
        Charity readyToUpdate = new Charity();
        if (charity.getId() == null) {
            return;
        } else {
            Charity origin = charityRepository.findOne(charity.getId());
            readyToUpdate.setId(charity.getId());

            String des = charity.getDescription() == null ? origin.getDescription() : charity.getDescription();
            readyToUpdate.setDescription(des);

            String name = charity.getName() == null ? origin.getName() : charity.getName();
            readyToUpdate.setName(name);

            String logoPath = charity.getLogoFile() == null ? origin.getLogoFile() : charity.getLogoFile();
            readyToUpdate.setLogoFile(logoPath);

            Long thumb = charity.getThumbUp() == null ? origin.getThumbUp() : charity.getThumbUp();
            readyToUpdate.setThumbUp(thumb);

            String oauthUserId = charity.getOauthUserId() == null ? origin.getOauthUserId() : charity.getOauthUserId();
            readyToUpdate.setOauthUserId(oauthUserId);
        }
        charityRepository.save(readyToUpdate);
        //charityRepository.updateById(readyToUpdate.getId(), readyToUpdate.getName(), readyToUpdate.getDescription());
    }

    @Override
    public void updateDirect(Charity charity) {
        charityRepository.save(charity);
    }

    @Override
    public void deleteById(Long id) {
        charityRepository.delete(id);
    }

    @Override
    public Charity findById(Long id) {
        return charityRepository.findOne(id);
    }

    @Override
    public Charity findByOauthUserId(String oauthUserId) {
        return charityRepository.findByOauthUserId(oauthUserId);
    }

    @Override
    public Charity[] findPaged(Pageable pageable) {
        Page<Charity> page = charityRepository.findAll(pageable);
        return page.getContent().toArray(new Charity[0]);
    }

    @Override
    public Page<Charity> findByNameLike(String name, Pageable pageable) {
        Page<Charity> page = charityRepository.findByNameLike(name, pageable);
        return page;
    }

    @Override
    public Set<Charity> getCharitiesByCause(String cause) {
        Cause res = causeRepository.findCauseByName(cause);
        return res.getCharities();
    }

    @Override
    public Set<Charity> getCharitiesByCountry(String country) {
        Country res = countryRepository.findCountryByCountryValue(country);
        return res.getCharities();
    }

    @Override
    public Page<Charity> findAll(Pageable pageable) {
        return charityRepository.findAll(pageable);
    }

    @Override
    public List<Charity> findAll(Sort sort) {
        return charityRepository.findAll(sort);
    }

    @Override
    public Charity thumbUp(Long id) {
        Charity charity = findById(id);
        charity.setThumbUp(charity.getThumbUp()+1);
        updateDirect(charity);
        return findById(id);
    }
}
