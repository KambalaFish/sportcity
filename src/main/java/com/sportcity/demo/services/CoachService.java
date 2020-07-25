package com.sportcity.demo.services;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoachService extends Service<CoachDTO, Integer>{

    Page<SportsmanDTO> getSportsmenOfTheCoach(Integer coachId, Pageable pageable);
}
