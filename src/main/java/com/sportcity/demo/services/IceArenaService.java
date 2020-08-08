package com.sportcity.demo.services;

import com.sportcity.demo.dtos.IceArenaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IceArenaService extends Service<IceArenaDTO, Integer> {
    Page<IceArenaDTO> getPageWithIceArenaById(Integer id, Pageable pageable);
}
