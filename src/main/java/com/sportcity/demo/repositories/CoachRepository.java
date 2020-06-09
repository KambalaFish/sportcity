package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoachRepository extends JpaRepository<Coach, Long> {
}
