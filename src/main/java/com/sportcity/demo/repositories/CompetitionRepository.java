package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
}
