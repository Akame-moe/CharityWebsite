package com.charityconnector.dao;

import com.charityconnector.bean.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CharityDao  extends JpaRepository<Charity, Long> {

    // The table name should be exactly as the entity name in the bean package
    @Query("select c from Charity c where c.name = ?1")
    Charity[] findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("UPDATE Charity c SET c.name = ?2, c.description = ?3 WHERE c.id = ?1")
    void updateByDescription(@Param("id") Long id, @Param("name") String name, @Param("description") String description);
}
