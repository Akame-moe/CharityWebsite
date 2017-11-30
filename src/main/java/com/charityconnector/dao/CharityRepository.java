package com.charityconnector.dao;

import com.charityconnector.entity.Cause;
import com.charityconnector.entity.Charity;
import com.charityconnector.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface CharityRepository extends JpaRepository<Charity, Long> {

    Page<Charity> findByNameLike(@Param("name") String name, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Charity c SET c.name = :name, c.description = :description WHERE c.id = :id")
    void updateById(@Param("id") Long id, @Param("name") String name, @Param("description") String description);

    @Query(value = "SELECT * FROM Charity c ORDER BY random() LIMIT 1;", nativeQuery = true)
    Charity findRandom();

    Charity findByOauthUserId(String oauthUserId);

    @Query("select c from Charity c where :cause member of c.causes AND :country member of  c.countries  AND c.name LIKE  CONCAT('%',:name,'%')")
    Charity[] findByCauseAndCountry(@Param("cause") Cause cause, @Param("country") Country country, @Param("name") String name);

    @Query("select c from Charity c where :country member of c.countries AND c.name LIKE  CONCAT('%',:name,'%')")
    Charity[] findByCountry(@Param("country") Country country, @Param("name") String name);

    @Query("select c from Charity c where :cause member of c.causes AND c.name LIKE  CONCAT('%',:name,'%')")
    Charity[] findByCause(@Param("cause") Cause cause, @Param("name") String name);

    // The table name should be exactly as the entity name in the entity package
    @Query("select c from Charity c where c.name LIKE  CONCAT('%',:name,'%')")
    Charity[] findByNameLike(@Param("name") String name);
}
