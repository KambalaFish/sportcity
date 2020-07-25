package com.sportcity.demo.mappers;

import com.sportcity.demo.entities.Court;
import com.sportcity.demo.dtos.CourtDTO;
import com.sportcity.demo.repositories.Sport_facilitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CourtMapper extends AbstractMapper<Court, CourtDTO, Integer>{

    @Autowired
    private Sport_facilitiesRepository sport_facilitiesRepository;

    @Autowired
    protected CourtMapper(ModelMapper mapper){
        super(mapper, Court.class, CourtDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Court::setSport_facility);
    }

    @Override
    protected void mapDTOToEntity(CourtDTO DTO, Court entity) {
        if (sport_facilitiesRepository.findById(DTO.getId()).isPresent())
            entity.setSport_facility(getEntityByIdOrThrow(sport_facilitiesRepository, DTO.getId()));


    }
}
