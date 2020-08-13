package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.types.Sport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {

    @Query("select distinct c from Coach c join c.sportsmen s where s.id = :sportsmanId")
    Page<Coach> getAllCoachesBySportsmanId(@Param("sportsmanId") Integer sportsmanId, Pageable pageable);

    @Query("select distinct c from Coach c where (:sport is null or c.sport = :sport) ")
    Page<Coach> searchByFilter(@Param("sport") Sport sport, Pageable pageable);

}
