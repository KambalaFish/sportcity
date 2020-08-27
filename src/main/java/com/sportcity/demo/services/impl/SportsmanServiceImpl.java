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
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Arrays;
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
    /*
    deprecated
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
        filterSearch(sportsmanList, filter);


        while (page.hasNext() & (sportsmanList.size() < pageable.getPageSize())){
            page = repository.searchByFilter(
                    filter.getSport(),
                    filter.getMinLevel(),
                    filter.getMaxLevel(),
                    filter.getCoachId(),
                    page.nextPageable()
            );
            List<Sportsman> newPortion = new ArrayList<>(page.getContent());
            filterSearch(newPortion, filter);
            sportsmanList.addAll(newPortion);
        }

        //Page<Sportsman> pageE = new PageImpl<>(sportsmanList, page.getPageable(), page.getTotalElements());
        Page<Sportsman> pageE = new PageImpl<>(sportsmanList, PageRequest.of(page.getNumber(), sportsmanList.size(), pageable.getSort()), sportsmanList.size());
        return pageE.map(getMapper()::toDTO);
    }
    */

    /*
    deprecated
    @Override
    public Page<SportsmanDTO> search(SportsmanFilter filter, Pageable pageable) {

        Page<Sportsman> page = repository.searchByFilter(
                filter.getSport(),
                filter.getMinLevel(),
                filter.getMaxLevel(),
                filter.getCoachId(),
                filter.getSportsOfSportsman(),
                pageable
        );
        List<Sportsman> listForRequest = new ArrayList<>(page.getContent());

        List<Sportsman> totalList = new ArrayList<>(page.getContent());

        filterSearch(listForRequest, filter);


        while ( (listForRequest.size() < pageable.getPageSize()) & page.hasNext() ){
            System.out.println("while ( (listForRequest.size() < pageable.getPageSize()) & page.hasNext() )");
            page = repository.searchByFilter(
                    filter.getSport(),
                    filter.getMinLevel(),
                    filter.getMaxLevel(),
                    filter.getCoachId(),
                    filter.getSportsOfSportsman(),
                    page.nextPageable()
            );
            List<Sportsman> newPortion = new ArrayList<>(page.getContent());
            filterSearch(newPortion, filter);
            listForRequest.addAll(newPortion);
            totalList.addAll(newPortion);
        }

        while (page.hasNext()){
            page = repository.searchByFilter(
                    filter.getSport(),
                    filter.getMinLevel(),
                    filter.getMaxLevel(),
                    filter.getCoachId(),
                    filter.getSportsOfSportsman(),
                    page.nextPageable()
            );
            List<Sportsman> newPortion = new ArrayList<>(page.getContent());
            filterSearch(newPortion, filter);
            totalList.addAll(newPortion);
        }

        System.out.println(pageable.getPageNumber());
        System.out.println(totalList.size());
        System.out.println();

        Page<Sportsman> pageE;
        if (listForRequest.size()<pageable.getPageSize())
            pageE = new PageImpl<>(listForRequest, pageable, listForRequest.size());
        else
            pageE = new PageImpl<>(
                    listForRequest,
                    PageRequest.of(pageable.getPageNumber()+1, listForRequest.size(),
                            pageable.getSort()
                    ), totalList.size());

        return pageE.map(getMapper()::toDTO);
    }
    */

    @Override
    public Page<SportsmanDTO> search(SportsmanFilter filter, Pageable pageable) {
        Page<Sportsman> page;
        String name = prepareStringToLikeStatement(filter.getName());
        if (filter.getSportList() == null){
            page = repository.searchByFilter(
                    name,
                    filter.getClubId(),
                    filter.getSport(),
                    filter.getMinLevel(),
                    filter.getMaxLevel(),
                    filter.getCoachId(),
                    filter.getMinPeriod(),
                    filter.getMaxPeriod(),
                    pageable
            );
        } else {
            page = repository.searchByFilterExtended(
                    name,
                    filter.getClubId(),
                    filter.getSport(),
                    filter.getMinLevel(),
                    filter.getMaxLevel(),
                    filter.getCoachId(),
                    filter.getMinPeriod(),
                    filter.getMaxPeriod(),
                    filter.getSportList(),
                    (long) filter.getSportList().size(),
                    pageable
            );
        }
        /*
        if (filter.isSportsmenWithOverOneSport())
            page = repository.searchByFilterAlternative(
                    filter.getSport(),
                    filter.getMinLevel(),
                    filter.getMaxLevel(),
                    filter.getCoachId(),
                    filter.getMinPeriod(),
                    filter.getMaxPeriod(),
                    pageable
            );
        else
            page = repository.searchByFilter(
                    filter.getSport(),
                    filter.getMinLevel(),
                    filter.getMaxLevel(),
                    filter.getCoachId(),
                    filter.getMinPeriod(),
                    filter.getMaxPeriod(),
                    pageable
            );
        */
        return page.map(getMapper()::toDTO);
    }

    @Deprecated
    private void filterSearch(List<Sportsman> sportsmanList, SportsmanFilter filter){

        if(filter.getSportList().size()>0)
            sportsmanList.removeIf(
                    sportsman -> {
                        boolean result = false;
                        for (Sport sport : filter.getSportList()){
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


        if (filter.getMinPeriod()!=null & filter.getMaxPeriod()!=null){
            sportsmanList.removeIf(
                    sportsman -> {
                        boolean result = false;
                        boolean condition1, condition2;
                        for (Competition competition : sportsman.getCompetitions()){
                            condition1 = competition.getBeginningDate().after(filter.getMaxPeriod());
                            condition2 = competition.getFinishDate().before(filter.getMinPeriod());
                            if (!condition1 & !condition2){
                                result = true;
                                break;
                            }
                        }
                        return result;
                    }
            );
        }
    }

}
