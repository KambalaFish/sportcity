package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportFacilityDTO;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Court;
import com.sportcity.demo.dtos.CourtDTO;
import com.sportcity.demo.entities.SportFacility;
import com.sportcity.demo.repositories.SportFacilityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourtMapper extends AbstractMapperSF<Court, CourtDTO, Integer>{

    @Autowired
    private IMapperSF<SportFacility, SportFacilityDTO, Integer> sportFacilityMapper;

    @Autowired
    private IMapper<Competition, CompetitionDTO, Integer> competitionMapper;

    @Autowired
    protected CourtMapper(ModelMapper mapper){
        super(mapper, Court.class, CourtDTO.class);
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Court::setSportFacility);
        skipDTOField(CourtDTO::setSportFacility);
    }

    @Override
    protected void mapDTOToEntity(CourtDTO dto, Court entity) {
        SportFacility sportFacility = new SportFacility();
        sportFacility.setId(dto.getId());
        List<Competition> competitions = new ArrayList<>();
        dto.getSportFacility().getCompetitions().forEach(competitionDTO -> {
            Competition competition = competitionMapper.toEntity(competitionDTO);
            competitions.add(competition);
        });
        sportFacility.setCompetitions(competitions);
        sportFacility.setCourt(entity);
        entity.setSportFacility(sportFacility);
    }

    @Override
    protected void mapEntityToDTO(Court entity, CourtDTO dto){
        dto.setSportFacility(sportFacilityMapper.toDTO(entity.getSportFacility()));
    }
}
