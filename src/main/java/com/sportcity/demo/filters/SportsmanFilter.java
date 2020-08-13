package com.sportcity.demo.filters;

import com.sportcity.demo.entities.types.Sport;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SportsmanFilter {
    private Sport sport;
    private Integer minLevel;
    private Integer maxLevel;
    private Integer coachId;
    private List<Sport> sportsOfSportsman;
}
