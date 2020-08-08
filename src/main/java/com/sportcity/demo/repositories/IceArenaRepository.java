package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.IceArena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IceArenaRepository extends JpaRepository<IceArena, Integer> {
    Page<IceArena> findById(Integer id, Pageable pageable);
}
