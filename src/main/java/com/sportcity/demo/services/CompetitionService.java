package com.sportcity.demo.services;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionService extends Service<CompetitionDTO, Integer>{
    Page<SportsmanDTO> getSportsmenOfTheCompetition(Integer competitionId, Pageable pageable);

    void removeLinkWithSportsman(Integer competitionId, Integer sportsmanId);

    Page<OrganizerDTO> getOrganizersOfTheCompetition(Integer competitionId, Pageable pageable);

    void removeLinkWithOrganizer(Integer competitionId, Integer organizerId);
}
