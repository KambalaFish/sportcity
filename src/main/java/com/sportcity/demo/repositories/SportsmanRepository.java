package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Sportsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, Long> {
}
