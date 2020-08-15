package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.*;
import com.sportcity.demo.entities.*;
import com.sportcity.demo.filters.CompetitionFilter;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.mappers.IMapperSF;
import com.sportcity.demo.repositories.CompetitionRepository;
import com.sportcity.demo.repositories.OrganizerRepository;
import com.sportcity.demo.repositories.SportFacilityRepository;
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

    private final SportFacilityRepository sportFacilityRepository;
    private final IMapperSF<SportFacility, SportFacilityDTO, Integer> sportFacilityMapper;

    private final IMapperSF<Court, CourtDTO, Integer> courtMapper;

    private final IMapperSF<Stadium, StadiumDTO, Integer> stadiumMapper;

    private final IMapperSF<IceArena, IceArenaDTO, Integer> iceArenaMapper;

    private final IMapperSF<VolleyballArena, VolleyballArenaDTO, Integer> volleyballArenaMapper;

    @Autowired
    public CompetitionServiceImpl(
            CompetitionRepository repository,
            IMapper<Competition, CompetitionDTO, Integer> mapper,
            SportsmanRepository sportsmanRepository,
            IMapper<Sportsman, SportsmanDTO, Integer> sporsmanMapper,
            OrganizerRepository organizerRepository,
            IMapper<Organizer, OrganizerDTO, Integer> organizerMapper,
            SportFacilityRepository sportFacilityRepository,
            IMapperSF<SportFacility, SportFacilityDTO, Integer> sportFacilityMapper,
            IMapperSF<Court, CourtDTO, Integer> courtMapper,
            IMapperSF<Stadium, StadiumDTO, Integer> stadiumMapper,
            IMapperSF<IceArena, IceArenaDTO, Integer> iceArenaMapper,
            IMapperSF<VolleyballArena, VolleyballArenaDTO, Integer> volleyballArenaMapper
            ){
        this.repository = repository;
        this.mapper = mapper;
        this.sportsmanRepository = sportsmanRepository;
        this.sportsmanMapper = sporsmanMapper;
        this.organizerRepository = organizerRepository;
        this.organizerMapper = organizerMapper;
        this.sportFacilityRepository = sportFacilityRepository;
        this.sportFacilityMapper = sportFacilityMapper;
        this.courtMapper = courtMapper;
        this.stadiumMapper = stadiumMapper;
        this.iceArenaMapper = iceArenaMapper;
        this.volleyballArenaMapper = volleyballArenaMapper;
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

    @Override
    public Page<SportFacilityDTO> getSportFacilitiesOfTheCompetition(Integer competitionId, Pageable pageable) {
        return sportFacilityRepository.getSportFacilitiesOfTheCompetition(competitionId, pageable).map(sportFacilityMapper::toDTO);
    }

    @Override
    public void removeLinkWithSportFacility(Integer competitionId, Integer sportFacilityId) {
        Competition competition = repository.findById(competitionId).get();
        SportFacility sportFacility = sportFacilityRepository.findById(sportFacilityId).get();

        competition.getSportFacilities().remove(sportFacility);
        sportFacility.getCompetitions().remove(competition);

        repository.save(competition);
        sportFacilityRepository.save(sportFacility);
    }

    @Override
    public Page<CourtDTO> getCourtsOfTheCompetition(Integer competitionId, Pageable pageable) {
        return sportFacilityRepository.getSportFacilitiesOfTheCompetition(competitionId, pageable).map(sportFacility -> courtMapper.toDTO(sportFacility.getCourt()));
    }

    @Override
    public Page<StadiumDTO> getStadiumsOfTheCompetition(Integer competitionId, Pageable pageable) {
        return sportFacilityRepository.getSportFacilitiesOfTheCompetition(competitionId, pageable).map(sportFacility -> stadiumMapper.toDTO(sportFacility.getStadium()));
    }

    @Override
    public Page<IceArenaDTO> getIceArenasOfTheCompetition(Integer competitionId, Pageable pageable) {
        return sportFacilityRepository.getSportFacilitiesOfTheCompetition(competitionId, pageable).map(sportFacility -> iceArenaMapper.toDTO(sportFacility.getIceArena()));
    }

    @Override
    public Page<VolleyballArenaDTO> getVolleyballArenasOfTheCompetition(Integer competitionId, Pageable pageable) {
        return sportFacilityRepository.getSportFacilitiesOfTheCompetition(competitionId, pageable).map(sportFacility -> volleyballArenaMapper.toDTO(sportFacility.getVolleyballArena()));
    }

    @Override
    public Page<CompetitionDTO> search(CompetitionFilter competitionFilter, Pageable pageable) {
        return repository.searchByFilter(competitionFilter.getMinPeriod(), competitionFilter.getMaxPeriod(), competitionFilter.getOrganizerId(), competitionFilter.getSport(), pageable).map(getMapper()::toDTO);
    }

    @Override
    public Page<SportsmanDTO> getPrizeWinnersOfTheCompetition(Integer competitionId, Pageable pageable) {
        return sportsmanRepository.getAllPrizeWinnersOfTheCompetition(competitionId, pageable).map(sportsmanMapper::toDTO);
    }

    @Override
    public void removeLinkWithPrizeWinner(Integer competitionId, Integer prizeWinnerId) {
        Competition competition = repository.findById(competitionId).get();
        Sportsman prizeWinner = sportsmanRepository.findById(prizeWinnerId).get();

        competition.getPrizeWinners().remove(prizeWinner);
        prizeWinner.getWonCompetitions().remove(competition);

        repository.save(competition);
        sportsmanRepository.save(prizeWinner);
    }
}
