package com.sportcity.demo.filters;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DateFilter {
    private Date minPeriod;
    private Date maxPeriod;
}
