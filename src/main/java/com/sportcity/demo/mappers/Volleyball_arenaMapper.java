package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.Volleyball_arenaDTO;
import com.sportcity.demo.entities.Volleyball_arena;
import com.sportcity.demo.repositories.Sport_facilitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Volleyball_arenaMapper extends AbstractMapper<Volleyball_arena, Volleyball_arenaDTO, Integer>{

    @Autowired
    private Sport_facilitiesRepository sport_facilitiesRepository;

    @Autowired
    protected Volleyball_arenaMapper(ModelMapper mapper){
        super(mapper, Volleyball_arena.class, Volleyball_arenaDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Volleyball_arena::setSport_facility);
    }

    @Override
    protected void mapDTOToEntity(Volleyball_arenaDTO DTO, Volleyball_arena entity) {
        if (sport_facilitiesRepository.findById(DTO.getId()).isPresent())
            entity.setSport_facility(sport_facilitiesRepository.findById(DTO.getId()).get());
    }
}
