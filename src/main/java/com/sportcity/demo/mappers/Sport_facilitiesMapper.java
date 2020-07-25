package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.Sport_facilitiesDTO;
import com.sportcity.demo.entities.Sport_facilities;
import com.sportcity.demo.entities.Stadium;
import com.sportcity.demo.entities.Volleyball_arena;
import com.sportcity.demo.entities.types.Sport;
import com.sportcity.demo.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Sport_facilitiesMapper extends AbstractMapper<Sport_facilities, Sport_facilitiesDTO, Integer>{

    @Autowired
    private Volleyball_arenaRepository volleyball_arenaRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private Ice_arenaRepository ice_arenaRepository;

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    protected Sport_facilitiesMapper(ModelMapper mapper){
        super(mapper, Sport_facilities.class, Sport_facilitiesDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Sport_facilities::setVolleyball_arena);
        skipEntityField(Sport_facilities::setCompetitions);
        skipEntityField(Sport_facilities::setCourt);
        skipEntityField(Sport_facilities::setIce_arena);
        skipEntityField(Sport_facilities::setStadium);
    }

    @Override
    protected void mapDTOToEntity(Sport_facilitiesDTO DTO, Sport_facilities entity) {
        if (stadiumRepository.findById(DTO.getId()).isPresent())
            entity.setStadium(getEntityByIdOrThrow(stadiumRepository, DTO.getId()));

        if (volleyball_arenaRepository.findById(DTO.getId()).isPresent())
            entity.setVolleyball_arena(getEntityByIdOrThrow(volleyball_arenaRepository, DTO.getId()));
        if (ice_arenaRepository.findById(DTO.getId()).isPresent())
            entity.setIce_arena(getEntityByIdOrThrow(ice_arenaRepository, DTO.getId()));
        if (courtRepository.findById(DTO.getId()).isPresent())
            entity.setCourt(getEntityByIdOrThrow(courtRepository, DTO.getId()));
    }
}
