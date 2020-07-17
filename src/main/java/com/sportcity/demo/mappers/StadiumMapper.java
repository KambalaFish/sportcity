package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.StadiumDTO;
import com.sportcity.demo.entities.Stadium;
import com.sportcity.demo.repositories.Sport_facilitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StadiumMapper extends AbstractMapper<Stadium, StadiumDTO, Integer>{

    @Autowired
    private Sport_facilitiesRepository sport_facilitiesRepository;

    @Autowired
    protected StadiumMapper(ModelMapper mapper){
        super(mapper, Stadium.class, StadiumDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Stadium.class, StadiumDTO.class).setPostConverter(toDTOConverter());
        mapper.createTypeMap(StadiumDTO.class, Stadium.class).addMappings(m -> m.skip(Stadium::setSport_facility)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapDTOToEntity(StadiumDTO DTO, Stadium entity) {
        if (sport_facilitiesRepository.findById(DTO.getId()).isPresent())
            entity.setSport_facility(sport_facilitiesRepository.findById(DTO.getId()).get());
    }
}
