package com.sportcity.demo.services;

import com.sportcity.demo.dtos.VolleyballArenaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VolleyballService extends Service<VolleyballArenaDTO, Integer>{

    Page<VolleyballArenaDTO> getPageWithVolleyballById(Integer id, Pageable pageable);

}
