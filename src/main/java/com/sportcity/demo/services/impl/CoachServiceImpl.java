package com.sportcity.demo.services.impl;


import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.filters.CoachFilter;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.CoachRepository;
import com.sportcity.demo.repositories.SportsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.sportcity.demo.services.CoachService;

@Service
public class CoachServiceImpl extends AbstractService<Coach, CoachDTO, Integer> implements CoachService {

    private final CoachRepository repository;
    private final SportsmanRepository sportsmanRepository;
    private final IMapper<Coach, CoachDTO, Integer> mapper;
    private final IMapper<Sportsman, SportsmanDTO, Integer> sportsmanMapper;

    @Autowired
    public CoachServiceImpl(CoachRepository repository,
                            IMapper<Coach, CoachDTO, Integer> mapper,
                            SportsmanRepository sportsmanRepository,
                            IMapper<Sportsman, SportsmanDTO, Integer> sportsmanMapper
                            ){
        this.repository = repository;
        this.mapper = mapper;
        this.sportsmanRepository = sportsmanRepository;
        this.sportsmanMapper = sportsmanMapper;
    }

    @Override
    protected JpaRepository<Coach, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapper<Coach, CoachDTO, Integer> getMapper() {
        return mapper;
    }

    @Override
    public Page<SportsmanDTO> getSportsmenOfTheCoach(Integer coachId, Pageable pageable) {
        return sportsmanRepository.getAllSportsmenByCoachId(coachId, pageable).map(sportsmanMapper::toDTO);
    }

    @Override
    public void removeLinkWithSportsman(Integer coachId, Integer sportsmanId) {
        Coach coach = repository.findById(coachId).get();
        Sportsman sportsman = sportsmanRepository.findById(sportsmanId).get();

        coach.getSportsmen().remove(sportsman);
        sportsman.getCoaches().remove(coach);

        repository.save(coach);
        sportsmanRepository.save(sportsman);
    }

    @Override
    public Page<CoachDTO> search(CoachFilter coachFilter, Pageable pageable) {
        return repository.searchByFilter(coachFilter.getSport(), pageable).map(getMapper()::toDTO);
    }
}
