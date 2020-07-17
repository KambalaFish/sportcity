package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.Volleyball_arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Volleyball_arenaRepository extends JpaRepository<Volleyball_arena, Integer> {
}
