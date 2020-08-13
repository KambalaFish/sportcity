package com.sportcity.demo.filters;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompetitionFilter {
    private Date minPeriod;
    private Date maxPeriod;
    private Integer organizerId;
}
