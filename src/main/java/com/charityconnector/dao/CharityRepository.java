package com.charityconnector.dao;

import com.charityconnector.entity.Charity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CharityRepository extends JpaRepository<Charity, Long> {

    // The table name should be exactly as the entity name in the entity package
    @Query("select c from Charity c where c.name = :name")
    Charity[] findByNameLike(@Param("name") String name);


    Page<Charity> findByNameLike(@Param("name") String name, Pageable pageable);

    @Query("select c from Charity c where c.causes = :causes")
    Charity[] findByCauses(@Param("causes") long causes);

    @Modifying
    @Transactional
    @Query("UPDATE Charity c SET c.name = :name, c.description = :description WHERE c.id = :id")
    void updateById(@Param("id") Long id, @Param("name") String name, @Param("description") String description);

    @Query(value = "SELECT * FROM Charity c ORDER BY random() LIMIT 1;", nativeQuery = true)
    Charity findRandom();
}
