package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Volleyball_arena;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Volleyball_arenaDTO extends AbstractDTO<Integer>{

    private Integer net_height;
    private Integer net_width;
}
