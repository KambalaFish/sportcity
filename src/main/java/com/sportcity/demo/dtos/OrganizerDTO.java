package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Organizer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizerDTO extends AbstractDTO<Integer>{


    private String name;
}
