package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.repositories.SportsmanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AbilityMapper extends AbstractMapper<Ability, AbilityDTO, Integer>{

    @Autowired
    private SportsmanRepository sportsmanRepository;

    @Autowired
    protected AbilityMapper(ModelMapper mapper) {
        super(mapper, Ability.class, AbilityDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Ability.class, AbilityDTO.class).addMappings(m -> m.skip(AbilityDTO::setSportsmanId)).setPostConverter(toDTOConverter());
        mapper.createTypeMap(AbilityDTO.class, Ability.class).addMappings(m -> m.skip(Ability::setSportsman)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapEntityToDTO(Ability entity, AbilityDTO DTO) {
        DTO.setSportsmanId(entity.getSportsman().getId());
    }

    @Override
    protected void mapDTOToEntity(AbilityDTO DTO, Ability entity) {
        entity.setSportsman(sportsmanRepository.findById(DTO.getSportsmanId()).isEmpty()? null : sportsmanRepository.findById(DTO.getSportsmanId()).get());
    }
}
