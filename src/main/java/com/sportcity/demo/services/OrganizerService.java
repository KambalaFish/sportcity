package com.sportcity.demo.services;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizerService extends Service<OrganizerDTO, Integer>{

    Page<CompetitionDTO> getCompetitionsOfTheOrganizer(Integer organizerId, Pageable pageable);

    void removeLinkWithCompetition(Integer organizerId, Integer competitionId);
}
