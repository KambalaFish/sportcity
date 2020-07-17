package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Court;
import com.sportcity.demo.entities.types.Coverage_type;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourtDTO extends AbstractDTO<Integer>{

    private Coverage_type coverage_type;
}
