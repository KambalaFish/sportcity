package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> { }
