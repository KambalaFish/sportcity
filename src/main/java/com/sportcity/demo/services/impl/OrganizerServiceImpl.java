package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Organizer;
import com.sportcity.demo.filters.DateFilter;
/*import com.sportcity.demo.filters.OrganizerFilter;*/
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.CompetitionRepository;
import com.sportcity.demo.repositories.OrganizerRepository;
import com.sportcity.demo.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl extends AbstractService<Organizer, OrganizerDTO, Integer> implements OrganizerService {

    private final OrganizerRepository repository;
    private final IMapper<Organizer, OrganizerDTO, Integer> mapper;
    private final CompetitionRepository competitionRepository;
    private final IMapper<Competition, CompetitionDTO, Integer> competitionMapper;

    @Autowired
    public OrganizerServiceImpl(
            OrganizerRepository repository,
            IMapper<Organizer, OrganizerDTO, Integer> mapper,
            CompetitionRepository competitionRepository,
            IMapper<Competition, CompetitionDTO, Integer> competitionMapper
            ){
        this.repository = repository;
        this.mapper = mapper;
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public Page<CompetitionDTO> getCompetitionsOfTheOrganizer(Integer organizerId, Pageable pageable) {
        return competitionRepository.getAllCompetitionsByOrganizerId(organizerId, pageable).map(competitionMapper::toDTO);
    }

    @Override
    public void removeLinkWithCompetition(Integer organizerId, Integer competitionId) {
        Organizer organizer = repository.findById(organizerId).get();
        Competition competition = competitionRepository.findById(competitionId).get();

        organizer.getCompetitions().remove(competition);
        competition.getOrganizers().remove(organizer);

        repository.save(organizer);
        competitionRepository.save(competition);
    }

    /*12 запрос, я так понимаю, что проведенных соревнований в течение определенного периода времени,
    значит дата начала и конца соревнования должна строго лежать внутри указанного периода*/
    @Override
    public Integer getNumberOfCompetitionForPeriod(Integer organizerId, DateFilter/*OrganizerFilter*/ filter) {
        List<Competition> competitions = competitionRepository.getAllCompetitionOfOrganizerInPeriod(organizerId, filter.getMinPeriod(), filter.getMaxPeriod());
        return competitions.size();
    }

    @Override
    protected JpaRepository<Organizer, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapper<Organizer, OrganizerDTO, Integer> getMapper() {
        return mapper;
    }
}
