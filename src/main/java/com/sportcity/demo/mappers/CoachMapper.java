package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Sportsman;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoachMapper extends AbstractMapper<Coach, CoachDTO, Integer>{


    @Autowired
    public CoachMapper(ModelMapper mapper){
        super(mapper, Coach.class, CoachDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Coach::setSportsmen);
        skipDTOField(CoachDTO::setSportsmen);
    }

    @Override
    protected void mapEntityToDTO(Coach entity, CoachDTO DTO) {

    }

    @Override
    protected void mapDTOToEntity(CoachDTO DTO, Coach entity) {

    }
}
