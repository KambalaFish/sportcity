package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Court;
import com.sportcity.demo.entities.types.CoverageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Integer> {
    Page<Court> findById(Integer id, Pageable pageable);


    @Query("select distinct c from Court c where (:coverageType is null or c.coverageType = :coverageType)")
    Page<Court> searchByFilter(@Param("coverageType")CoverageType coverageType, Pageable pageable);
}
