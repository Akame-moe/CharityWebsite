package com.charityconnector.dao;

import com.charityconnector.entity.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CharityRepository extends JpaRepository<Charity, Long> {

    // The table name should be exactly as the entity name in the entity package
    @Query("select c from Charity c where c.name = :name")
    Charity[] findByName(@Param("name") String name);

    @Query("select c from Charity c where c.cause = :cause")
    Charity[] findByCause(@Param("cause") String cause);


    @Modifying
    @Transactional
    @Query("UPDATE Charity c SET c.name = :name, c.description = :description WHERE c.id = :id")
    void updateById(@Param("id") Long id, @Param("name") String name, @Param("description") String description);
}
