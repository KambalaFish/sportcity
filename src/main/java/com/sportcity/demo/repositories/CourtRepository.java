package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Integer> {
}
