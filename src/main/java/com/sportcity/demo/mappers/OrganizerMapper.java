package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.entities.Organizer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrganizerMapper extends AbstractMapper<Organizer, OrganizerDTO, Long>{

    @Autowired
    public OrganizerMapper(ModelMapper mapper){
        super(mapper, Organizer.class, OrganizerDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Organizer.class, OrganizerDTO.class).setPostConverter(toDTOConverter());
        mapper.createTypeMap(OrganizerDTO.class, Organizer.class).setPostConverter(toEntityConverter());
    }
}
