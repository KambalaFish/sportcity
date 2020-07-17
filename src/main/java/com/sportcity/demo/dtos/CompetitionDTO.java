package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.types.Sport;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CompetitionDTO extends AbstractDTO<Integer> {
    private Date date;
    private Sport sport;
}
