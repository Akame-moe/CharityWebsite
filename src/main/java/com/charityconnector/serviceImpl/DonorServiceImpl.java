package com.charityconnector.serviceImpl;

import com.charityconnector.dao.DonorRepository;
import com.charityconnector.entity.Donor;
import com.charityconnector.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DonorServiceImpl implements DonorService {

    @Resource
    DonorRepository donorRepository;

    @Override
    public Donor findById(Long id) {
        return donorRepository.findById(id);
    }

    @Override
    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }
}
