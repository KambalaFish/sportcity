package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Organizer;
import com.sportcity.demo.entities.Sportsman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    @Query("select distinct o from Organizer o join o.competitions c where c.id = :competitionId")
    Page<Organizer> getOrganizersOfTheCompetition(@Param("competitionId") Integer competitionId, Pageable pageable);

}
