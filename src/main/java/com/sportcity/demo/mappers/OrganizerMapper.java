package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Organizer;
import com.sportcity.demo.repositories.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizerMapper extends AbstractMapper<Organizer, OrganizerDTO, Integer>{

    private final CompetitionRepository competitionRepository;

    @Autowired
    public OrganizerMapper(ModelMapper mapper,
                           CompetitionRepository competitionRepository){
        super(mapper, Organizer.class, OrganizerDTO.class);
        this.competitionRepository = competitionRepository;
    }

    @PostConstruct
    private void setupMapper(){
        skipDTOField(OrganizerDTO::setCompetitions);
        skipEntityField(Organizer::setCompetitions);
    }

    protected void mapDTOToEntity(OrganizerDTO sourceDTO, Organizer destination){
        List<Competition> competitions = new ArrayList<>();
        for(CompetitionDTO competitionDTO : sourceDTO.getCompetitions()){
            Competition competition = getEntityByIdOrThrow(competitionRepository, competitionDTO.getId());
            competitions.add(competition);
        }
        destination.setCompetitions(competitions);
    }

    protected void mapEntityToDTO(Organizer source, OrganizerDTO destination){
        List<CompetitionDTO> competitionsDTO = new ArrayList<>();
        for(Competition competition : source.getCompetitions()){
            CompetitionDTO competitionDTO = new CompetitionDTO();
            competitionDTO.setId(competition.getId());
            competitionDTO.setName(competition.getName());
            competitionDTO.setSport(competition.getSport());
            competitionDTO.setBeginningDate(competition.getBeginningDate());
            competitionDTO.setFinishDate(competition.getFinishDate());
            competitionsDTO.add(competitionDTO);
        }
        destination.setCompetitions(competitionsDTO);
    }

}
