package com.sportcity.demo.services;

import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SportsmanService extends Service<SportsmanDTO, Integer>{
    Page<AbilityDTO> getSportsmanAbilities(Integer sportsmanId, Pageable pageable);

    Page<CoachDTO> getCoachesOfTheSportsman(Integer sportsmanId, Pageable pageable);

    SportsmanDTO removeLinkWithCoach(Integer sportsmanId, Integer coachId);
}
