package com.sportcity.demo.services;

import com.sportcity.demo.dtos.CourtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourtService extends Service<CourtDTO, Integer> {

    Page<CourtDTO> getPageWithCourtById(Integer id, Pageable pageable);

}
