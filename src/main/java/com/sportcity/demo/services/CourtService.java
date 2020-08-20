package com.sportcity.demo.services;

import com.sportcity.demo.dtos.CourtDTO;
import com.sportcity.demo.filters.CourtFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourtService extends Service<CourtDTO, Integer> {

    Page<CourtDTO> getPageWithCourtById(Integer id, Pageable pageable);

    Page<CourtDTO> search(CourtFilter courtFilter, Pageable pageable);
}
