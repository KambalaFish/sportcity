package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.entities.types.Sport;
import com.sportcity.demo.repositories.CoachRepository;
import com.sportcity.demo.repositories.CompetitionRepository;
import com.sportcity.demo.repositories.SportsmanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SportsmanMapper extends AbstractMapper<Sportsman, SportsmanDTO, Integer> {

    private final CoachRepository coachRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public SportsmanMapper(ModelMapper mapper,
                           CoachRepository coachRepository,
                           CompetitionRepository competitionRepository){
        super(mapper, Sportsman.class, SportsmanDTO.class);
        this.coachRepository = coachRepository;
        this.competitionRepository = competitionRepository;
    }

    @PostConstruct
    private void setupMapper(){
        skipDTOField(SportsmanDTO::setCoaches);
        skipEntityField(Sportsman::setAbilities);
        skipEntityField(Sportsman::setCoaches);
        skipEntityField(Sportsman::setCompetitions);
    }

    protected void mapDTOToEntity(SportsmanDTO sourceDTO, Sportsman destination){
        List<Coach> coaches = new ArrayList<>();
        for(CoachDTO coachDTO : sourceDTO.getCoaches()){
            Coach coach = getEntityByIdOrThrow(coachRepository, coachDTO.getId());
            coaches.add(coach);
        }
        destination.setCoaches(coaches);

        List<Competition> competitions = new ArrayList<>();
        for(CompetitionDTO competitionDTO : sourceDTO.getCompetitions()){
            Competition competition = getEntityByIdOrThrow(competitionRepository, competitionDTO.getId());
            competitions.add(competition);
        }
        destination.setCompetitions(competitions);
    }

    protected void mapEntityToDTO(Sportsman sportsman, SportsmanDTO sportsmanDTO){
        List<CoachDTO> coachesDTO = new ArrayList<>();
        for(Coach coach : sportsman.getCoaches()){
            //coachesDTO.add(coachMapper.toDTO(coach));
            CoachDTO coachDTO = new CoachDTO();
            coachDTO.setId(coach.getId());
            coachDTO.setName(coach.getName());
            coachDTO.setSport(coach.getSport());
            coachesDTO.add(coachDTO);
        }
        sportsmanDTO.setCoaches(coachesDTO);

        List<CompetitionDTO> competitionsDTO = new ArrayList<>();
        for(Competition competition : sportsman.getCompetitions()){
            CompetitionDTO competitionDTO = new CompetitionDTO();
            competitionDTO.setId(competition.getId());
            competitionDTO.setName(competition.getName());
            competitionDTO.setDate(competition.getDate());
            competitionDTO.setSport(competition.getSport());
            competitionsDTO.add(competitionDTO);
        }
        sportsmanDTO.setCompetitions(competitionsDTO);
    }


}
