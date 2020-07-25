package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Ability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Integer> {
    Page<Ability> getAllBySportsmanId(Integer sportsmanId, Pageable pageable);
}
