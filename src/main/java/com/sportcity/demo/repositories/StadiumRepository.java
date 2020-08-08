package com.sportcity.demo.repositories;


import com.sportcity.demo.entities.Stadium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
    Page<Stadium> findById(Integer id, Pageable pageable);

    @Query("select distinct s from Stadium s " +
            "where (:minCapacity is null or s.capacity >= :minCapacity)" +
            "and (:maxCapacity is null or s.capacity <= :maxCapacity)")
    Page<Stadium> searchByFilter(
            @Param("minCapacity") Integer minCapcity,
            @Param("maxCapacity") Integer maxCapacity,
            Pageable pageable
    );
}
