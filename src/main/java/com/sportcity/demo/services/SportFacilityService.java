package com.sportcity.demo.services;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportFacilityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SportFacilityService extends Service<SportFacilityDTO, Integer>{

    Integer getLastIdNumber();

    Page<CompetitionDTO> getCompetitionsOfTheSportFacility(Integer sportFacilityId, Pageable pageable);

    void removeLinkWithCompetition(Integer sportFacilityId, Integer competitionId);

}
