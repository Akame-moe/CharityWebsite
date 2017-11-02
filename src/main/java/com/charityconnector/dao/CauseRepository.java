package com.charityconnector.dao;

import com.charityconnector.entity.Cause;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauseRepository extends JpaRepository<Cause, Long> {

    Cause findCauseByCauseValue(String causeValue);
}
