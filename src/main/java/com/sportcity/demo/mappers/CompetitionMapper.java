package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Competition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CompetitionMapper extends AbstractMapper<Competition, CompetitionDTO, Integer> {

    @Autowired
    public CompetitionMapper(ModelMapper mapper){
        super(mapper, Competition.class, CompetitionDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Competition.class, CompetitionDTO.class).setPostConverter(toDTOConverter());
        mapper.createTypeMap(CompetitionDTO.class, Competition.class).setPostConverter(toEntityConverter());
    }
}
