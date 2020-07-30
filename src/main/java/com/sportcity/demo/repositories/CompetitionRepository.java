package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    @Query("select distinct c from Competition c join c.sportsmen s where s.id = :sportsmanId")
    Page<Competition> getAllCompetitionsBySportsmanId(@Param("sportsmanId") Integer sportsmanId, Pageable pageable);

    @Query("select distinct c from Competition c join c.organizers s where s.id = :organizerId")
    Page<Competition> getAllCompetitionsByOrganizerId(@Param("organizerId") Integer organizerId, Pageable pageable);

}
