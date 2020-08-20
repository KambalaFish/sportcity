package com.sportcity.demo.repositories;

import com.sportcity.demo.entities.VolleyballArena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VolleyballArenaRepository extends JpaRepository<VolleyballArena, Integer> {
    Page<VolleyballArena> findById(Integer id, Pageable pageable);

    @Query("select distinct v from VolleyballArena v where " +
            "(:minNetHeight is null or v.net_height >= :minNetHeight) and " +
            "(:maxNetHeight is null or v.net_height <= :maxNetHeight) and " +
            "(:minNetWidth is null or v.net_width >= :minNetWidth) and " +
            "(:maxNetWidth is null or v.net_width <= :maxNetWidth)"
    )
    Page<VolleyballArena> searchByFilter(
            @Param("minNetHeight") Double minNetHeight,
            @Param("maxNetHeight") Double maxNetHeight,
            @Param("minNetWidth") Double minNetWidth,
            @Param("maxNetWidth") Double maxNetWidth,
            Pageable pageable
            );
}
