package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.Sportsman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, Integer> {

    Sportsman getById(Integer id);

    @Query("select distinct s from Sportsman s join s.coaches c where c.id = :coachId")
    Page<Sportsman> getAllSportsmenByCoachId(@Param("coachId") Integer coachId, Pageable pageable);

}
