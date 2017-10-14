package com.charityconnector.serviceImpl;

import com.charityconnector.bean.Charity;
import com.charityconnector.dao.CharityDao;
import com.charityconnector.service.CharityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CharityServiceImpl implements CharityService {

    @Resource
    private CharityDao charityDao;

    @Override
    public Charity getCharity() {
        return charityDao.findOne(1l);
    }

    @Override
    public Charity addCharity(Charity charity) {
        Charity c = charityDao.save(charity);
        return c;
    }

    @Override
    public Charity[] findByName(String name) {
        Charity[] charities = charityDao.findByName(name);
        return charities;
    }

    @Override
    public void update(Charity charity) {
        charityDao.updateByDescription(charity.getId(), charity.getName(), charity.getDescription());
    }

    @Override
    public void deleteById(Long id) {
        charityDao.delete(id);
    }

    @Override
    public Charity findById(Long id) {
        return charityDao.findOne(id);
    }
}
