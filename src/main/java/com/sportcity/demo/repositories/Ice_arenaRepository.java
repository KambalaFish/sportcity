package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Ice_arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ice_arenaRepository extends JpaRepository<Ice_arena, Long> {
}
