package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.entities.types.Sport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, Integer> {

    @Query("select distinct s from Sportsman s join s.coaches c where c.id = :coachId")
    Page<Sportsman> getAllSportsmenByCoachId(@Param("coachId") Integer coachId, Pageable pageable);

    @Query("select distinct s from Sportsman s join s.competitions c where c.id = :competitionId")
    Page<Sportsman> getAllSportsmenByCompetitionId(@Param("competitionId") Integer competitionId, Pageable pageable);

    @Query("select distinct s from Sportsman s " +
            "left join s.abilities a " +
            "left join s.coaches c " +
            "where (:sport is null or a.sport = :sport) " +
            "and (:minLevel is null or a.level >= :minLevel)" +
            "and (:maxLevel is null or a.level <= :maxLevel)" +
            "and (:coachId is null or c.id = :coachId and c.sport = a.sport)")
    Page<Sportsman> searchByFilter(
            @Param("sport") Sport sport,
            @Param("minLevel") Integer minLevel,
            @Param("maxLevel") Integer maxLevel,
            @Param("coachId") Integer coachId,
            Pageable pageable
    );

    @Query("select distinct s from Sportsman s join s.wonCompetitions wc where wc.id = :competitionId")
    Page<Sportsman> getAllPrizeWinnersOfTheCompetition(@Param("competitionId") Integer competitionId, Pageable pageable);

}
