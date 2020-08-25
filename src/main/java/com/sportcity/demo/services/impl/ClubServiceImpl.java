package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.ClubDTO;
import com.sportcity.demo.entities.Club;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.filters.DateFilter;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.ClubRepository;
import com.sportcity.demo.repositories.SportsmanRepository;
import com.sportcity.demo.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ClubServiceImpl extends AbstractService<Club, ClubDTO, Integer> implements ClubService {

    private final ClubRepository repository;
    private final IMapper<Club, ClubDTO, Integer> mapper;

    private final SportsmanRepository sportsmanRepository;

    @Autowired
    public ClubServiceImpl(
            ClubRepository repository,
            IMapper<Club, ClubDTO, Integer> mapper,
            SportsmanRepository sportsmanRepository
    ){
        this.repository = repository;
        this.mapper = mapper;
        this.sportsmanRepository = sportsmanRepository;
    }


    @Override
    protected JpaRepository<Club, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapper<Club, ClubDTO, Integer> getMapper() {
        return mapper;
    }

    @Override
    public Integer getNumberOfSportsmenOfTheClubDuringPeriod(Integer clubId, DateFilter filter) {
        /*
        List<Sportsman> list = sportsmanRepository.getSportsmanOfTheClub(clubId);

        list.removeIf(sportsman -> {
            boolean condition1 = false, condition2 = false;
            for (Competition competition : sportsman.getCompetitions()) {
                condition1 = competition.getBeginningDate().after(filter.getMaxPeriod());
                condition2 = competition.getFinishDate().before(filter.getMinPeriod());
                if (condition1 | condition2)
                    break;
            }
            return condition1 | condition2;
        }
        );

        return list.size();
        */
        return sportsmanRepository.getNumberOfSportsmenDuringPeriod(clubId, filter.getMinPeriod(), filter.getMaxPeriod());
    }


}
