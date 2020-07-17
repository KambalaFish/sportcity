package com.sportcity.demo.dtos;


import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.types.Sport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbilityDTO extends AbstractDTO<Integer>{

    private Integer sportsmanId;
    private Sport sport;
    private Integer level;
}
