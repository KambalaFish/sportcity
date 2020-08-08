package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.types.Sport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbilityDTO extends AbstractDTO<Integer>{

    private SportsmanDTO sportsman;
    private Sport sport;
    private Integer level;
}
