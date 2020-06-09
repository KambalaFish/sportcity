package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.entities.Coach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CoachMapper extends AbstractMapper<Coach, CoachDTO, Long>{

    @Autowired
    public CoachMapper(ModelMapper mapper){
        super(mapper, Coach.class, CoachDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Coach.class, CoachDTO.class).setPostConverter(toDTOConverter());
        mapper.createTypeMap(CoachDTO.class, Coach.class).addMappings(m -> m.skip(Coach::setSportsmen)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapEntityToDTO(Coach entity, CoachDTO DTO) {

    }

    @Override
    protected void mapDTOToEntity(CoachDTO DTO, Coach entity) {

    }
}
