package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.entities.Organizer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrganizerMapper extends AbstractMapper<Organizer, OrganizerDTO, Integer>{

    @Autowired
    public OrganizerMapper(ModelMapper mapper){
        super(mapper, Organizer.class, OrganizerDTO.class);
    }

}
