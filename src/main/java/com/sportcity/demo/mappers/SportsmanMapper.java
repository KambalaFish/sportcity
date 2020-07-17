package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Sportsman;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SportsmanMapper extends AbstractMapper<Sportsman, SportsmanDTO, Integer> {

    @Autowired
    public SportsmanMapper(ModelMapper mapper){
        super(mapper, Sportsman.class, SportsmanDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Sportsman.class, SportsmanDTO.class).setPostConverter(toDTOConverter());
        mapper.createTypeMap(SportsmanDTO.class, Sportsman.class).addMappings(m -> {m.skip(Sportsman::setAbilities); m.skip(Sportsman::setCoaches); m.skip(Sportsman::setCompetitions);}).setPostConverter(toEntityConverter());
    }
}
