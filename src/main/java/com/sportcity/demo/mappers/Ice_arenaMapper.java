package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.CourtDTO;
import com.sportcity.demo.dtos.IceArenaDTO;
import com.sportcity.demo.dtos.SportFacilityDTO;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Court;
import com.sportcity.demo.entities.IceArena;
import com.sportcity.demo.entities.SportFacility;
import com.sportcity.demo.repositories.SportFacilityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Ice_arenaMapper extends AbstractMapperSF<IceArena, IceArenaDTO, Integer>{

    @Autowired
    private IMapperSF<SportFacility, SportFacilityDTO, Integer> sportFacilityMapper;

    @Autowired
    private IMapper<Competition, CompetitionDTO, Integer> competitionMapper;

    @Autowired
    protected Ice_arenaMapper(ModelMapper mapper){
        super(mapper, IceArena.class, IceArenaDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(IceArena::setSportFacility);
        skipDTOField(IceArenaDTO::setSportFacility);
    }

    @Override
    protected void mapDTOToEntity(IceArenaDTO dto, IceArena entity) {
        SportFacility sportFacility = new SportFacility();
        sportFacility.setId(dto.getId());
        List<Competition> competitions = new ArrayList<>();
        dto.getSportFacility().getCompetitions().forEach(competitionDTO -> {
            Competition competition = competitionMapper.toEntity(competitionDTO);
            competitions.add(competition);
        });
        sportFacility.setCompetitions(competitions);
        sportFacility.setIceArena(entity);
        entity.setSportFacility(sportFacility);
    }

    @Override
    protected void mapEntityToDTO(IceArena entity, IceArenaDTO dto){
        dto.setSportFacility(sportFacilityMapper.toDTO(entity.getSportFacility()));
    }

}
