package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.Sport_facilitiesDTO;
import com.sportcity.demo.entities.Sport_facilities;
import com.sportcity.demo.entities.Stadium;
import com.sportcity.demo.entities.Volleyball_arena;
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
        mapper.createTypeMap(Sport_facilities.class, Sport_facilitiesDTO.class).setPostConverter(toDTOConverter());
        mapper.createTypeMap(Sport_facilitiesDTO.class, Sport_facilities.class).addMappings(m -> {
            m.skip(Sport_facilities::setVolleyball_arena);
            m.skip(Sport_facilities::setCompetitions);
            m.skip(Sport_facilities::setCourt);
            m.skip(Sport_facilities::setIce_arena);
            m.skip(Sport_facilities::setStadium);
        }).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapDTOToEntity(Sport_facilitiesDTO DTO, Sport_facilities entity) {
        if (stadiumRepository.findById(DTO.getId()).isPresent())
            entity.setStadium(stadiumRepository.findById(DTO.getId()).get());
        if (volleyball_arenaRepository.findById(DTO.getId()).isPresent())
            entity.setVolleyball_arena(volleyball_arenaRepository.findById(DTO.getId()).get());
        if (ice_arenaRepository.findById(DTO.getId()).isPresent())
            entity.setIce_arena(ice_arenaRepository.findById(DTO.getId()).get());
        if (courtRepository.findById(DTO.getId()).isPresent())
            entity.setCourt(courtRepository.findById(DTO.getId()).get());
    }
}
