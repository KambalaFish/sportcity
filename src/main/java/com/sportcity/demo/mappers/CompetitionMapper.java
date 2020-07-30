package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Competition;
import com.sportcity.demo.entities.Organizer;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.repositories.OrganizerRepository;
import com.sportcity.demo.repositories.SportsmanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompetitionMapper extends AbstractMapper<Competition, CompetitionDTO, Integer> {

    private final SportsmanRepository sportsmanRepository;
    private final OrganizerRepository organizerRepository;

    @Autowired
    public CompetitionMapper(ModelMapper mapper,
                             SportsmanRepository sportsmanRepository,
                             OrganizerRepository organizerRepository){
        super(mapper, Competition.class, CompetitionDTO.class);
        this.sportsmanRepository = sportsmanRepository;
        this.organizerRepository = organizerRepository;
    }

    @PostConstruct
    private void setupMapper(){
        skipDTOField(CompetitionDTO::setSportsmen);
        skipEntityField(Competition::setSportsmen);

        skipDTOField(CompetitionDTO::setOrganizers);
        skipEntityField(Competition::setOrganizers);

        skipEntityField(Competition::setSports_facility);

    }

    protected void mapDTOToEntity(CompetitionDTO sourceDTO, Competition destination){
        List<Sportsman> sportsmen = new ArrayList<>();
        for(SportsmanDTO sportsmanDTO : sourceDTO.getSportsmen()){
            Sportsman sportsman = getEntityByIdOrThrow(sportsmanRepository, sportsmanDTO.getId());
            sportsmen.add(sportsman);
        }
        destination.setSportsmen(sportsmen);

        List<Organizer> organizers = new ArrayList<>();
        for(OrganizerDTO organizerDTO : sourceDTO.getOrganizers()){
            Organizer organizer = getEntityByIdOrThrow(organizerRepository, organizerDTO.getId());
            organizers.add(organizer);
        }
        destination.setOrganizers(organizers);
    }

    @Override
    protected void mapEntityToDTO(Competition entity, CompetitionDTO DTO) {
        List<SportsmanDTO> sportsmenDTO = new ArrayList<>();
        for(Sportsman sportsman : entity.getSportsmen()){
            SportsmanDTO sportsmanDTO = new SportsmanDTO();
            sportsmanDTO.setId(sportsman.getId());
            sportsmanDTO.setName(sportsman.getName());
            sportsmanDTO.setClub_name(sportsman.getClub_name());
            sportsmenDTO.add(sportsmanDTO);
        }
        DTO.setSportsmen(sportsmenDTO);

        List<OrganizerDTO> organizersDTO = new ArrayList<>();
        for(Organizer organizer : entity.getOrganizers()){
            OrganizerDTO organizerDTO = new OrganizerDTO();
            organizerDTO.setId(organizer.getId());
            organizerDTO.setName(organizer.getName());
            organizersDTO.add(organizerDTO);
        }
        DTO.setOrganizers(organizersDTO);
    }

}
