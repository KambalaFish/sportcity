package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportFacilityDTO;
import com.sportcity.demo.dtos.StadiumDTO;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Court;
import com.sportcity.demo.entities.SportFacility;
import com.sportcity.demo.entities.Stadium;
import com.sportcity.demo.repositories.SportFacilityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StadiumMapper extends AbstractMapperSF<Stadium, StadiumDTO, Integer>{

    @Autowired
    private IMapper<Competition, CompetitionDTO, Integer> competitionMapper;

    @Autowired
    private IMapperSF<SportFacility, SportFacilityDTO, Integer> sportFacilityMapper;

    @Autowired
    protected StadiumMapper(ModelMapper mapper){
        super(mapper, Stadium.class, StadiumDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Stadium::setSportFacility);
    }

    @Override
    protected void mapDTOToEntity(StadiumDTO dto, Stadium entity) {
        SportFacility sportFacility = new SportFacility();
        sportFacility.setId(dto.getId());
        List<Competition> competitions = new ArrayList<>();
        dto.getSportFacility().getCompetitions().forEach(competitionDTO -> {
            Competition competition = competitionMapper.toEntity(competitionDTO);
            competitions.add(competition);
        });
        sportFacility.setCompetitions(competitions);
        sportFacility.setStadium(entity);
        entity.setSportFacility(sportFacility);
    }

    @Override
    protected void mapEntityToDTO(Stadium entity, StadiumDTO dto){
        dto.setSportFacility(sportFacilityMapper.toDTO(entity.getSportFacility()));
    }
}
