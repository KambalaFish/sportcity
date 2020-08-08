package com.sportcity.demo.services;

import com.sportcity.demo.dtos.StadiumDTO;
import com.sportcity.demo.filters.StadiumFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StadiumService extends Service<StadiumDTO, Integer>{
    Page<StadiumDTO> getPageWithStadiumById(Integer id, Pageable pageable);

    Page<StadiumDTO> search(StadiumFilter filter, Pageable pageable);
}
