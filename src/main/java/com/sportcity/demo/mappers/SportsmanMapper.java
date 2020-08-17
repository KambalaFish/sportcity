package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.*;
import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.entities.types.Sport;
import com.sportcity.demo.repositories.*;
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
    private final AbilityRepository abilityRepository;
    private final ClubRepository clubRepository;
    @Autowired
    public SportsmanMapper(
            ModelMapper mapper,
            CoachRepository coachRepository,
            CompetitionRepository competitionRepository,
            AbilityRepository abilityRepository,
            ClubRepository clubRepository
    ){
        super(mapper, Sportsman.class, SportsmanDTO.class);
        this.coachRepository = coachRepository;
        this.competitionRepository = competitionRepository;
        this.abilityRepository = abilityRepository;
        this.clubRepository = clubRepository;
    }

    @PostConstruct
    private void setupMapper(){
        skipDTOField(SportsmanDTO::setCoaches);
        skipEntityField(Sportsman::setCoaches);

        skipDTOField(SportsmanDTO::setAbilities);
        skipEntityField(Sportsman::setAbilities);

        skipDTOField(SportsmanDTO::setCompetitions); /*new_*/
        skipEntityField(Sportsman::setCompetitions);

        skipDTOField(SportsmanDTO::setWonCompetitions);/*new*/
        skipEntityField(Sportsman::setWonCompetitions);

        skipDTOField(SportsmanDTO::setClub);
        skipEntityField(Sportsman::setClub);
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

        List<Ability> abilities = new ArrayList<>();
        for(AbilityDTO abilityDTO : sourceDTO.getAbilities()){
            Ability ability = getEntityByIdOrThrow(abilityRepository, abilityDTO.getId());
            abilities.add(ability);
        }
        destination.setAbilities(abilities);

        List<Competition> wonCompetitions = new ArrayList<>();
        for(CompetitionDTO wonCompetitionDTO : sourceDTO.getWonCompetitions()){
            Competition wonCompetition = getEntityByIdOrThrow(competitionRepository, wonCompetitionDTO.getId());
            wonCompetitions.add(wonCompetition);
        }
        destination.setWonCompetitions(wonCompetitions);

        destination.setClub(getEntityByIdOrThrow(clubRepository, sourceDTO.getClub().getId()));
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
            competitionDTO.setBeginningDate(competition.getBeginningDate());
            competitionDTO.setFinishDate(competition.getFinishDate());
            competitionDTO.setSport(competition.getSport());
            competitionsDTO.add(competitionDTO);
        }
        sportsmanDTO.setCompetitions(competitionsDTO);

        /*new*/
        List<AbilityDTO> abilitiesDTO = new ArrayList<>();
        for(Ability ability : sportsman.getAbilities()){
            AbilityDTO abilityDTO = new AbilityDTO();
            abilityDTO.setId(ability.getId());
            abilityDTO.setLevel(ability.getLevel());
            abilityDTO.setSport(ability.getSport());
            abilitiesDTO.add(abilityDTO);
        }
        sportsmanDTO.setAbilities(abilitiesDTO);

        List<CompetitionDTO> wonCompetitionsDTO = new ArrayList<>();
        for(Competition wonCompetition : sportsman.getWonCompetitions()){
            CompetitionDTO dto = new CompetitionDTO();
            dto.setId(wonCompetition.getId());
            dto.setName(wonCompetition.getName());
            dto.setBeginningDate(wonCompetition.getBeginningDate());
            dto.setFinishDate(wonCompetition.getFinishDate());
            dto.setSport(wonCompetition.getSport());
            wonCompetitionsDTO.add(dto);
        }
        sportsmanDTO.setWonCompetitions(wonCompetitionsDTO);

        ClubDTO clubDTO = new ClubDTO();
        clubDTO.setId(sportsman.getClub().getId());
        clubDTO.setName(sportsman.getClub().getName());
        sportsmanDTO.setClub(clubDTO);
    }


}
