package com.sportcity.demo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClubDTO extends AbstractDTO<Integer>{
    private String name;
    private Integer amount_of_members;
    private List<SportsmanDTO> sportsmen = new ArrayList<>();
}
