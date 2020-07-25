package com.sportcity.demo.dtos;


import com.sportcity.demo.entities.Sportsman;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SportsmanDTO extends AbstractDTO<Integer>{
    private String name;
    private String club_name;

    private List<CoachDTO> coaches = new ArrayList<>();
}
