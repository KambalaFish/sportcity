package com.sportcity.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IceArenaDTO extends AbstractDTO<Integer>{

    private SportFacilityDTO sportFacility;
    private Double square;

}
