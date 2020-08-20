package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.IceArena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IceArenaRepository extends JpaRepository<IceArena, Integer> {
    Page<IceArena> findById(Integer id, Pageable pageable);


    @Query(
            "select distinct i from IceArena i where " +
            "(:minSquare is null or i.square >= :minSquare) and " +
            "(:maxSquare is null or i.square <= :maxSquare)"
    )
    Page<IceArena> searchByFilter(@Param("minSquare") Double minSquare, @Param("maxSquare") Double maxSquare, Pageable pageable);
}
