package com.sportcity.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VolleyballArenaDTO extends AbstractDTO<Integer>{

    private SportFacilityDTO sportFacility;
    private Integer net_height;
    private Integer net_width;
}
