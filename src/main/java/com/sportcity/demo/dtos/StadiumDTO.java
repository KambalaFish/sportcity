package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Stadium;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StadiumDTO extends AbstractDTO<Integer>{

    private SportFacilityDTO sportFacility;
    private Integer capacity;
}
