package com.charityconnector.serviceImpl;

import com.charityconnector.bean.Charity;
import com.charityconnector.dao.CharityRepository;
import com.charityconnector.service.CharityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CharityServiceImpl implements CharityService {

    @Resource
    private CharityRepository charityRepository;

    @Override
    public Charity getCharity() {
        return charityRepository.findOne(1l);
    }

    @Override
    public Charity addCharity(Charity charity) {
        Charity c = charityRepository.save(charity);
        return c;
    }

    @Override
    public Charity[] findByName(String name) {
        Charity[] charities = charityRepository.findByName(name);
        return charities;
    }

    @Override
    public void update(Charity charity) {
        charityRepository.updateByDescription(charity.getId(), charity.getName(), charity.getDescription());
    }

    @Override
    public void deleteById(Long id) {
        charityRepository.delete(id);
    }

    @Override
    public Charity findById(Long id) {
        return charityRepository.findOne(id);
    }
}
