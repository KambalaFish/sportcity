package com.sportcity.demo.filters;

import com.sportcity.demo.entities.types.Sport;
import lombok.Getter;

import java.util.Date;

@Getter
public class CompetitionOfSFFilter {
    private Integer sportFacilityID;
    private Sport sport;
    private Date minPeriod;
    private Date maxPeriod;
}
