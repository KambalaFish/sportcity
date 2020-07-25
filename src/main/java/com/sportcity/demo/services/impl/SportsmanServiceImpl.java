package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.AbilityRepository;
import com.sportcity.demo.repositories.CoachRepository;
import com.sportcity.demo.repositories.SportsmanRepository;
import com.sportcity.demo.services.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SportsmanServiceImpl extends AbstractService<Sportsman, SportsmanDTO, Integer> implements SportsmanService {

    private final SportsmanRepository repository;
    private final AbilityRepository abilityRepository;
    private final CoachRepository coachRepository;
    private final IMapper<Sportsman, SportsmanDTO, Integer> mapper;
    private final IMapper<Ability, AbilityDTO, Integer> abilityMapper;
    private final IMapper<Coach, CoachDTO, Integer> coachMapper;

    @Autowired
    public SportsmanServiceImpl(SportsmanRepository repository,
                                AbilityRepository abilityRepository,
                                CoachRepository coachRepository,
                                IMapper<Sportsman, SportsmanDTO, Integer> mapper,
                                IMapper<Ability, AbilityDTO, Integer> abilityMapper,
                                IMapper<Coach, CoachDTO, Integer> coachMapper){
        this.repository = repository;
        this.abilityRepository = abilityRepository;
        this.coachRepository = coachRepository;
        this.mapper = mapper;
        this.abilityMapper = abilityMapper;
        this.coachMapper = coachMapper;
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
    public SportsmanDTO removeLinkWithCoach(Integer sportsmanId, Integer coachId) {
        Sportsman sportsman = repository.findById(sportsmanId).get();
        Coach coach = coachRepository.findById(coachId).get();
        sportsman.getCoaches().remove(coach);
        coach.getSportsmen().remove(sportsman);

        repository.save(sportsman);
        coachRepository.save(coach);

        return mapper.toDTO(sportsman);
    }
}
