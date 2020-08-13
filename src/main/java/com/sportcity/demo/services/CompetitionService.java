package com.sportcity.demo.services;

import com.sportcity.demo.dtos.*;
import com.sportcity.demo.filters.CompetitionFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionService extends Service<CompetitionDTO, Integer>{
    Page<SportsmanDTO> getSportsmenOfTheCompetition(Integer competitionId, Pageable pageable);

    void removeLinkWithSportsman(Integer competitionId, Integer sportsmanId);

    Page<OrganizerDTO> getOrganizersOfTheCompetition(Integer competitionId, Pageable pageable);

    void removeLinkWithOrganizer(Integer competitionId, Integer organizerId);

    Page<SportFacilityDTO> getSportFacilitiesOfTheCompetition(Integer competitionId, Pageable pageable);

    void removeLinkWithSportFacility(Integer competitionId, Integer sportFacilityId);

    Page<CourtDTO> getCourtsOfTheCompetition(Integer competitionId, Pageable pageable);

    Page<StadiumDTO> getStadiumsOfTheCompetition(Integer competitionId, Pageable pageable);

    Page<IceArenaDTO> getIceArenasOfTheCompetition(Integer competitionId, Pageable pageable);

    Page<VolleyballArenaDTO> getVolleyballArenasOfTheCompetition(Integer competitionId, Pageable pageable);

    Page<CompetitionDTO> search(CompetitionFilter competitionFilter, Pageable pageable);

}
