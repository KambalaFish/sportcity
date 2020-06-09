package com.sportcity.demo.dtos;


import com.sportcity.demo.entities.Sportsman;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SportsmanDTO extends AbstractDTO<Long>{
    static {
        setEntityClass(Sportsman.class);
    }
    private String name;
    private String club_name;
}
