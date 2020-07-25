package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.Ice_arenaDTO;
import com.sportcity.demo.entities.Ice_arena;
import com.sportcity.demo.repositories.Sport_facilitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Ice_arenaMapper extends AbstractMapper<Ice_arena, Ice_arenaDTO, Integer>{

    @Autowired
    private Sport_facilitiesRepository sport_facilitiesRepository;

    @Autowired
    protected Ice_arenaMapper(ModelMapper mapper){
        super(mapper, Ice_arena.class, Ice_arenaDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Ice_arena::setSport_facility);
    }

    @Override
    protected void mapDTOToEntity(Ice_arenaDTO DTO, Ice_arena entity) {
        if (sport_facilitiesRepository.findById(DTO.getId()).isPresent())
            entity.setSport_facility(sport_facilitiesRepository.findById(DTO.getId()).get());
    }
}
