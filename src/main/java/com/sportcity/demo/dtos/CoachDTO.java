package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.types.Sport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachDTO extends AbstractDTO<Integer>{

    private String name;
    private Sport sport;
}
