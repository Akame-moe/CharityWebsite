package com.charityconnector.dao;
import com.charityconnector.entity.UKRecordCharity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UKRecordCharityRepository extends JpaRepository<UKRecordCharity,String> {
       // UKRecordCharity findById(String email);
}
