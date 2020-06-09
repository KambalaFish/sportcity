package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Ice_arena;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Ice_arenaDTO extends AbstractDTO<Long>{
    static {
        setEntityClass(Ice_arena.class);
    }
    private Integer square;
}
