package com.sportcity.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VolleyballArenaDTO extends AbstractDTO<Integer>{

    private SportFacilityDTO sportFacility;
    private Double net_height;
    private Double net_width;
}
