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

            String logoPath = charity.getLogoPath() == null ? origin.getLogoPath() : charity.getLogoPath();
            readyToUpdate.setLogoPath(logoPath);
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
}
