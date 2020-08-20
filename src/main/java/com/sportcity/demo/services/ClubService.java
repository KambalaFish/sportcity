package com.sportcity.demo.services;

import com.sportcity.demo.dtos.ClubDTO;
import com.sportcity.demo.filters.DateFilter;

public interface ClubService extends Service<ClubDTO, Integer>{
    Integer getNumberOfSportsmenOfTheClubDuringPeriod(Integer clubId, DateFilter filter);
}
