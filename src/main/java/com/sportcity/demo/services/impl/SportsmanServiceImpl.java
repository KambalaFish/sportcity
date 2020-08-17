package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.entities.types.Sport;
import com.sportcity.demo.filters.SportsmanFilter;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.AbilityRepository;
import com.sportcity.demo.repositories.CoachRepository;
import com.sportcity.demo.repositories.CompetitionRepository;
import com.sportcity.demo.repositories.SportsmanRepository;
import com.sportcity.demo.services.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportsmanServiceImpl extends AbstractService<Sportsman, SportsmanDTO, Integer> implements SportsmanService {

    private final SportsmanRepository repository;
    private final AbilityRepository abilityRepository;
    private final CoachRepository coachRepository;
    private final CompetitionRepository competitionRepository;
    private final IMapper<Sportsman, SportsmanDTO, Integer> mapper;
    private final IMapper<Ability, AbilityDTO, Integer> abilityMapper;
    private final IMapper<Coach, CoachDTO, Integer> coachMapper;
    private final IMapper<Competition, CompetitionDTO, Integer> competitionMapper;

    @Autowired
    public SportsmanServiceImpl(SportsmanRepository repository,
                                AbilityRepository abilityRepository,
                                CoachRepository coachRepository,
                                CompetitionRepository competitionRepository,
                                IMapper<Sportsman, SportsmanDTO, Integer> mapper,
                                IMapper<Ability, AbilityDTO, Integer> abilityMapper,
                                IMapper<Coach, CoachDTO, Integer> coachMapper,
                                IMapper<Competition, CompetitionDTO, Integer> competitionMapper){
        this.repository = repository;
        this.abilityRepository = abilityRepository;
        this.coachRepository = coachRepository;
        this.competitionRepository = competitionRepository;
        this.mapper = mapper;
        this.abilityMapper = abilityMapper;
        this.coachMapper = coachMapper;
        this.competitionMapper = competitionMapper;
    }

    @Override
    protected JpaRepository<Sportsman, Integer> getRepository(){
        return repository;
    }

    @Override
    protected IMapper<Sportsman, SportsmanDTO, Integer> getMapper(){
        return mapper;
    }

    @Override
    public Page<AbilityDTO> getSportsmanAbilities(Integer sportsmanId, Pageable pageable) {
        return abilityRepository.getAllBySportsmanId(sportsmanId, pageable).map(abilityMapper::toDTO);
    }

    @Override
    public Page<CoachDTO> getCoachesOfTheSportsman(Integer sportsmanId, Pageable pageable) {
        return coachRepository.getAllCoachesBySportsmanId(sportsmanId, pageable).map(coachMapper::toDTO);
    }

    @Override
    public void removeLinkWithCoach(Integer sportsmanId, Integer coachId) {
        Sportsman sportsman = repository.findById(sportsmanId).get();
        Coach coach = coachRepository.findById(coachId).get();
        sportsman.getCoaches().remove(coach);
        coach.getSportsmen().remove(sportsman);

        repository.save(sportsman);
        coachRepository.save(coach);

        //return mapper.toDTO(sportsman);
    }

    public Page<CompetitionDTO> getCompetitionsOfTheSportsman(Integer sportsmanId, Pageable pageable){
        return competitionRepository.getAllCompetitionsBySportsmanId(sportsmanId, pageable).map(competitionMapper::toDTO);
    }

    @Override
    public SportsmanDTO removeLinkWithCompetition(Integer sportsmanId, Integer competitionId) {
        Sportsman sportsman = repository.findById(sportsmanId).get();
        Competition competition = competitionRepository.findById(competitionId).get();
        sportsman.getCompetitions().remove(competition);
        competition.getSportsmen().remove(sportsman);

        repository.save(sportsman);
        competitionRepository.save(competition);

        return mapper.toDTO(sportsman);
    }

    @Override
    public Page<SportsmanDTO> search(SportsmanFilter filter, Pageable pageable) {
        Page<Sportsman> page = repository.searchByFilter(
                filter.getSport(),
                filter.getMinLevel(),
                filter.getMaxLevel(),
                filter.getCoachId(),
                pageable
        );
        List<Sportsman> sportsmanList = new ArrayList<>(page.getContent());
        if(filter.getSportsOfSportsman().size()>0)
        sportsmanList.removeIf(
                sportsman -> {
                    boolean result = false;
                    for (Sport sport : filter.getSportsOfSportsman()){
                            for (Ability ability : sportsman.getAbilities()){
                                result = sport == ability.getSport();
                                if (result)
                                    break;
                            }
                        if (!result)
                            break;
                    }
                    return !result;
                }
        );

        Page<Sportsman> pageE = new PageImpl<>(sportsmanList, pageable, sportsmanList.size());

        return pageE.map(getMapper()::toDTO);
    }

}
