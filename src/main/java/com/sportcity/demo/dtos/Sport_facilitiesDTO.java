package com.sportcity.demo.dtos;

import com.sportcity.demo.entities.Sport_facilities;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class Sport_facilitiesDTO extends AbstractDTO<Long> {
    static {
        setEntityClass(Sport_facilities.class);
    }
}
