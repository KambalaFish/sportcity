package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.types.CoverageType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourtDTO extends AbstractDTO<Integer>{

    private SportFacilityDTO sportFacility;
    private CoverageType coverageType;

}
