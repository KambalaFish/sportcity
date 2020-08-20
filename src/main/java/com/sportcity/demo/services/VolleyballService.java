package com.sportcity.demo.services;

import com.sportcity.demo.dtos.VolleyballArenaDTO;
import com.sportcity.demo.filters.VolleyballArenaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VolleyballService extends Service<VolleyballArenaDTO, Integer>{

    Page<VolleyballArenaDTO> getPageWithVolleyballById(Integer id, Pageable pageable);

    Page<VolleyballArenaDTO> search(VolleyballArenaFilter filter, Pageable pageable);

}
