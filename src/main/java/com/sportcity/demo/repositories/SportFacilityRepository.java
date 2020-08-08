package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Organizer;
import com.sportcity.demo.entities.SportFacility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SportFacilityRepository extends JpaRepository<SportFacility, Integer> {

    @Query("select distinct s from SportFacility s join s.competitions c where c.id = :competitionId")
    Page<SportFacility> getSportFacilitiesOfTheCompetition(@Param("competitionId") Integer competitionId, Pageable pageable);

}
