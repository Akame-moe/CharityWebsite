package com.charityconnector.service;

import com.charityconnector.entity.Donor;

public interface DonorService {

    Donor findById(Long id);
}