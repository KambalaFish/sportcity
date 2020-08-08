package com.sportcity.demo.dtos;

import lombok.Getter;

import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SportFacilityDTO extends AbstractDTO<Integer> {

    private CourtDTO court;

    private StadiumDTO stadium;

    private VolleyballArenaDTO volleyballArena;

    private IceArenaDTO iceArena;

    private List<CompetitionDTO> competitions;

}
