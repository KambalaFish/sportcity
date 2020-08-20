package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportFacilityDTO;
import com.sportcity.demo.entities.*;
import com.sportcity.demo.filters.CompetitionOfSFFilter;
import com.sportcity.demo.mappers.CompetitionMapper;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.mappers.IMapperSF;
import com.sportcity.demo.repositories.*;
import com.sportcity.demo.services.SportFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportFacilityServiceImpl extends AbstractServiceSF<SportFacility, SportFacilityDTO, Integer> implements SportFacilityService {

    private final SportFacilityRepository repository;
    private final CourtRepository courtRepository;
    private final StadiumRepository stadiumRepository;
    private final VolleyballArenaRepository volleyballArenaRepository;
    private final IceArenaRepository iceArenaRepository;
    private final IMapperSF<SportFacility, SportFacilityDTO, Integer> mapper;

    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    private Integer lastId;

    @Autowired
    public SportFacilityServiceImpl(
            SportFacilityRepository repository,
            IMapperSF<SportFacility, SportFacilityDTO, Integer> mapper,
            CourtRepository courtRepository,
            StadiumRepository stadiumRepository,
            VolleyballArenaRepository volleyballArenaRepository,
            IceArenaRepository iceArenaRepository,
            CompetitionRepository competitionRepository,
            CompetitionMapper competitionMapper
    ){
        this.repository = repository;
        this.mapper = mapper;
        this.courtRepository = courtRepository;
        this.stadiumRepository = stadiumRepository;
        this.volleyballArenaRepository = volleyballArenaRepository;
        this.iceArenaRepository = iceArenaRepository;
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }

    @Override
    protected JpaRepository<SportFacility, Integer> getRepository(){
        return repository;
    }

    @Override
    protected IMapperSF<SportFacility, SportFacilityDTO, Integer> getMapper(){
        return mapper;
    }


    @Override
    public Page<CompetitionDTO> getCompetitionsOfTheSportFacility(Integer sportFacilityId, Pageable pageable) {
        return competitionRepository.getAllCompetitionsBySportFacilityId(sportFacilityId, pageable).map(competitionMapper::toDTO);
    }

    @Override
    public void removeLinkWithCompetition(Integer sportFacilityId, Integer competitionId) {
        SportFacility sportFacility = repository.findById(sportFacilityId).get();
        Competition competition = competitionRepository.findById(competitionId).get();

        sportFacility.getCompetitions().remove(competition);
        competition.getSportFacilities().remove(sportFacility);

        repository.save(sportFacility);
        competitionRepository.save(competition);
    }

    /*13 запрос. думаю, что имелось в виду попадание во внутрь определенного периода дат начала и конца соревнований*/
    @Override
    public Page<CompetitionDTO> getCompetitionsOfTheSportFacilityByFilter(CompetitionOfSFFilter filter, Pageable pageable) {
        return competitionRepository.getAllCompetitionsBySFFilter(
                filter.getSportFacilityID(),
                filter.getSport(),
                filter.getMinPeriod(),
                filter.getMaxPeriod(),
                pageable).map(competitionMapper::toDTO);
    }

    @Override
    public Integer getLastIdNumber() {
        /*
        if (repository.count()==0){
            lastId = 0;
        }
        if (lastId==null){
            List<SportFacility> list = repository.findAll();
            lastId = 0;
            list.forEach(sportFacility -> {
                Integer id = sportFacility.getId();
                if(id>lastId)
                    lastId = id;
            });
        }
        lastId++;
        return lastId - 1;
        */

        List<SportFacility> list = repository.findAll();
        lastId = 0;
        list.forEach(sportFacility -> {
            Integer id = sportFacility.getId();
            if (id > lastId)
                lastId = id;
        });
        return lastId;

    }
}
