package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Sport_facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Sport_facilitiesRepository extends JpaRepository<Sport_facilities, Long> {
}
