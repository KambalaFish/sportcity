package com.sportcity.demo.dtos;


import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Sportsman;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SportsmanDTO extends AbstractDTO<Integer>{
    private String name;
    private ClubDTO club;

    private List<CoachDTO> coaches = new ArrayList<>();
    private List<CompetitionDTO> competitions = new ArrayList<>();
    private List<AbilityDTO> abilities = new ArrayList<>();
    private List<CompetitionDTO> wonCompetitions = new ArrayList<>();
}
