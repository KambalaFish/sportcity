package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.repositories.SportsmanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AbilityMapper extends AbstractMapper<Ability, AbilityDTO, Integer>{

    private final IMapper<Sportsman, SportsmanDTO, Integer> sportsmanMapper;
    private final SportsmanRepository sportsmanRepository;

    @Autowired
    protected AbilityMapper(ModelMapper mapper, IMapper<Sportsman, SportsmanDTO, Integer> sportsmanMapper, SportsmanRepository sportsmanRepository) {
        super(mapper, Ability.class, AbilityDTO.class);
        this.sportsmanMapper = sportsmanMapper;
        this.sportsmanRepository = sportsmanRepository;
    }

    @PostConstruct
    private void setupMapper(){
        skipDTOField(AbilityDTO::setSportsman);
        skipEntityField(Ability::setSportsman);
    }

    @Override
    protected void mapEntityToDTO(Ability entity, AbilityDTO DTO) {
        DTO.setSportsman(sportsmanMapper.toDTO(entity.getSportsman()));
    }

    @Override
    protected void mapDTOToEntity(AbilityDTO DTO, Ability entity) {
        entity.setSportsman(getEntityByIdOrThrow(sportsmanRepository, DTO.getSportsman().getId()));
    }
}
