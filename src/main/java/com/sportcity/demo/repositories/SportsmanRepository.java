package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.entities.types.Sport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
            "where " +
            "(:name is null or lower(s.name) like :name) " +
            "and (:clubId is null or s.club.id = :id)" +
            "and (:sport is null or a.sport = :sport) " +
            "and (:minLevel is null or a.level >= :minLevel)" +
            "and (:maxLevel is null or a.level <= :maxLevel)" +
            "and (:coachId is null or c.id = :coachId and c.sport = a.sport) " +
            "and ( (:minPeriod is null and :maxPeriod is null) or " +
                "s not in " +
                "(" +
                    "select distinct sp from Sportsman sp left join sp.competitions com where " +
                    "(com.finishDate >= :minPeriod and com.finishDate <= :maxPeriod) or " +
                    "(com.beginningDate >= :minPeriod and com.beginningDate <= :maxPeriod) or " +
                    "(com.beginningDate <= :minPeriod and com.finishDate >= :maxPeriod)" +
                ")" +
            ")"
    )
    Page<Sportsman> searchByFilter(
            @Param("name") String name,
            @Param("clubId") Integer clubId,
            @Param("sport") Sport sport,
            @Param("minLevel") Integer minLevel,
            @Param("maxLevel") Integer maxLevel,
            @Param("coachId") Integer coachId,
            @Param("minPeriod") Date minPeriod,
            @Param("maxPeriod") Date maxPeriod,
            Pageable pageable
    );


    /*
    @Query("select distinct s from Sportsman s " +
            "left join s.abilities a " +
            "left join s.coaches c " +
            "where (:sport is null or a.sport = :sport) " +
            "and (:minLevel is null or a.level >= :minLevel)" +
            "and (:maxLevel is null or a.level <= :maxLevel)" +
            "and (:coachId is null or c.id = :coachId and c.sport = a.sport) " +
            "and ( (:minPeriod is null and :maxPeriod is null) or " +
            "s not in " +
            "(select distinct sp from Sportsman sp left join sp.competitions com where " +
            "(com.finishDate >= :minPeriod and com.finishDate <= :maxPeriod) or " +
            "(com.beginningDate >= :minPeriod and com.beginningDate <= :maxPeriod) )" +
            ")" +
            "and (s in " +
            "(select distinct spr from Sportsman spr where " +
            "(select count(abi) from Ability abi where(abi.sportsman.id = spr.id and abi.sport in (:list))) = :size ))"
    )
    Page<Sportsman> searchByFilterExtended(
            @Param("sport") Sport sport,
            @Param("minLevel") Integer minLevel,
            @Param("maxLevel") Integer maxLevel,
            @Param("coachId") Integer coachId,
            @Param("minPeriod") Date minPeriod,
            @Param("maxPeriod") Date maxPeriod,
            @Param("list") List<Sport> list,
            @Param("size") Long size,
            Pageable pageable
    );
    */

    @Query("select distinct s from Sportsman s " +
            "left join s.abilities a " +
            "left join s.coaches c " +
            "where " +
            "(:name is null or lower(s.name) like :name) " +
            "and (:clubId is null or s.club.id = :clubId)" +
            "and (:sport is null or a.sport = :sport) " +
            "and (:minLevel is null or a.level >= :minLevel)" +
            "and (:maxLevel is null or a.level <= :maxLevel)" +
            "and (:coachId is null or c.id = :coachId and c.sport = a.sport) " +
            "and ( " +
                "(:minPeriod is null and :maxPeriod is null) or " +
                "s not in " +
                "(" +
                    "select distinct sp from Sportsman sp left join sp.competitions com where " +
                    "(com.finishDate >= :minPeriod and com.finishDate <= :maxPeriod) or " +
                    "(com.beginningDate >= :minPeriod and com.beginningDate <= :maxPeriod) or " +
                    "(com.beginningDate <= :minPeriod and com.finishDate >= :maxPeriod)" +
                ")" +
            ")" +
            "and (s in " +
            "(select distinct spr from Sportsman spr where " +
            "(select count(abi) from Ability abi " +
            "where(abi.sportsman.id = spr.id and abi.sport in (:list) and (:minLevel is null or abi.level >= :minLevel) and (:maxLevel is null or abi.level <= :maxLevel))" +
            ") = :size))"
    )/*может выбирать всех спортсменов, с указанными видами спорта, причем по всем указанным видам спорта разряд должен входить в указанные рамки*/
    Page<Sportsman> searchByFilterExtended(
            @Param("name") String name,
            @Param("clubId") Integer clubId,
            @Param("sport") Sport sport,
            @Param("minLevel") Integer minLevel,
            @Param("maxLevel") Integer maxLevel,
            @Param("coachId") Integer coachId,
            @Param("minPeriod") Date minPeriod,
            @Param("maxPeriod") Date maxPeriod,
            @Param("list") List<Sport> list,
            @Param("size") Long size,
            Pageable pageable
    );

    @Query("select distinct s from Sportsman s " +
            "left join s.abilities a " +
            "left join s.coaches c " +
            "where (:sport is null or a.sport = :sport) " +
            "and (:minLevel is null or a.level >= :minLevel)" +
            "and (:maxLevel is null or a.level <= :maxLevel)" +
            "and (:coachId is null or c.id = :coachId and c.sport = a.sport) " +
            "and ( (:minPeriod is null and :maxPeriod is null) or " +
            "s not in " +
            "(select distinct sp from Sportsman sp left join sp.competitions com where " +
            "(com.finishDate >= :minPeriod and com.finishDate <= :maxPeriod) or " +
            "(com.beginningDate >= :minPeriod and com.beginningDate <= :maxPeriod) )" +
            ")" +
            "and (select count(ab) from Ability ab where ab.sportsman.id = s.id) > 1"
    )/*этот метод находит всех спортсменов, которые занимаются более, чем одним видом спорта*/
    Page<Sportsman> searchByFilterAlternative(
            @Param("sport") Sport sport,
            @Param("minLevel") Integer minLevel,
            @Param("maxLevel") Integer maxLevel,
            @Param("coachId") Integer coachId,
            @Param("minPeriod") Date minPeriod,
            @Param("maxPeriod") Date maxPeriod,
            Pageable pageable
    );


    @Query("select distinct s from Sportsman s join s.wonCompetitions wc where wc.id = :competitionId")
    Page<Sportsman> getAllPrizeWinnersOfTheCompetition(@Param("competitionId") Integer competitionId, Pageable pageable);


    @Query(nativeQuery = true, value = "call countNumberOfSportsmenDuringPeriod(:clubId, :minDate, :maxDate)")
    Integer getNumberOfSportsmenDuringPeriod(
            @Param("clubId") Integer clubId,
            @Param("minDate") Date minDate,
            @Param("maxDate") Date maxDate
    );
}
