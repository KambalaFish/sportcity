package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Organizer;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.CompetitionRepository;
import com.sportcity.demo.repositories.OrganizerRepository;
import com.sportcity.demo.repositories.SportsmanRepository;
import com.sportcity.demo.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompetitionServiceImpl extends AbstractService<Competition, CompetitionDTO, Integer> implements CompetitionService {

    private final CompetitionRepository repository;
    private final IMapper<Competition, CompetitionDTO, Integer> mapper;

    private final SportsmanRepository sportsmanRepository;
    private final IMapper<Sportsman, SportsmanDTO, Integer> sportsmanMapper;

    private final OrganizerRepository organizerRepository;
    private final IMapper<Organizer, OrganizerDTO, Integer> organizerMapper;

    @Autowired
    public CompetitionServiceImpl(
            CompetitionRepository repository,
            IMapper<Competition, CompetitionDTO, Integer> mapper,
            SportsmanRepository sportsmanRepository,
            IMapper<Sportsman, SportsmanDTO, Integer> sporsmanMapper,
            OrganizerRepository organizerRepository,
            IMapper<Organizer, OrganizerDTO, Integer> organizerMapper
            ){
        this.repository = repository;
        this.mapper = mapper;
        this.sportsmanRepository = sportsmanRepository;
        this.sportsmanMapper = sporsmanMapper;
        this.organizerRepository = organizerRepository;
        this.organizerMapper = organizerMapper;

    }

    @Override
    protected JpaRepository<Competition, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapper<Competition, CompetitionDTO, Integer> getMapper() {
        return mapper;
    }

    @Override
    public Page<SportsmanDTO> getSportsmenOfTheCompetition(Integer competitionId, Pageable pageable) {
        return sportsmanRepository.getAllSportsmenByCompetitionId(competitionId, pageable).map(sportsmanMapper::toDTO);
    }

    @Override
    public void removeLinkWithSportsman(Integer competitionId, Integer sportsmanId) {
        Competition competition = repository.findById(competitionId).get();
        Sportsman sportsman = sportsmanRepository.findById(sportsmanId).get();

        competition.getSportsmen().remove(sportsman);
        sportsman.getCompetitions().remove(competition);

        repository.save(competition);
        sportsmanRepository.save(sportsman);
    }

    @Override
    public Page<OrganizerDTO> getOrganizersOfTheCompetition(Integer competitionId, Pageable pageable) {
        return organizerRepository.getOrganizersOfTheCompetition(competitionId, pageable).map(organizerMapper::toDTO);
    }

    @Override
    public void removeLinkWithOrganizer(Integer competitionId, Integer organizerId) {
        Competition competition = repository.findById(competitionId).get();
        Organizer organizer = organizerRepository.findById(organizerId).get();

        competition.getOrganizers().remove(organizer);
        organizer.getCompetitions().remove(competition);

        repository.save(competition);
        organizerRepository.save(organizer);
    }
}
