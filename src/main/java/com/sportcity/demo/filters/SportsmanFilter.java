package com.sportcity.demo.filters;

import com.sportcity.demo.entities.types.Sport;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SportsmanFilter {
    private String name;
    private Integer clubId;
    private Sport sport;
    private Integer minLevel;
    private Integer maxLevel;
    private Integer coachId;
    private Date minPeriod;
    private Date maxPeriod;
    private List<Sport> sportList;
    //private boolean sportsmenWithOverOneSport;
}
