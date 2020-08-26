package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.VolleyballArenaDTO;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.SportFacility;
import com.sportcity.demo.entities.VolleyballArena;
import com.sportcity.demo.repositories.SportFacilityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Volleyball_arenaMapper extends AbstractMapperSF<VolleyballArena, VolleyballArenaDTO, Integer>{

    @Autowired
    private IMapper<Competition, CompetitionDTO, Integer> competitionMapper;

    @Autowired
    protected Volleyball_arenaMapper(ModelMapper mapper){
        super(mapper, VolleyballArena.class, VolleyballArenaDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(VolleyballArena::setSportFacility);
    }

    @Override
    protected void mapDTOToEntity(VolleyballArenaDTO dto, VolleyballArena entity) {
        SportFacility sportFacility = new SportFacility();
        sportFacility.setId(dto.getId());
        List<Competition> competitions = new ArrayList<>();
        dto.getSportFacility().getCompetitions().forEach(competitionDTO -> {
            Competition competition = competitionMapper.toEntity(competitionDTO);
            competitions.add(competition);
        });
        sportFacility.setCompetitions(competitions);
        sportFacility.setVolleyballArena(entity);
        entity.setSportFacility(sportFacility);
    }
}
