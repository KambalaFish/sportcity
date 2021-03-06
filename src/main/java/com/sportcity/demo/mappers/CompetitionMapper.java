package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.*;
import com.sportcity.demo.entities.*;
import com.sportcity.demo.repositories.OrganizerRepository;
import com.sportcity.demo.repositories.SportFacilityRepository;
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
    private final SportFacilityRepository sportFacilityRepository;
    private final IMapper<Club, ClubDTO, Integer> clubMapper;
    @Autowired
    public CompetitionMapper(
            ModelMapper mapper,
            SportsmanRepository sportsmanRepository,
            OrganizerRepository organizerRepository,
            SportFacilityRepository sportFacilityRepository,
            IMapper<Club, ClubDTO, Integer> clubMapper
    ){
        super(mapper, Competition.class, CompetitionDTO.class);
        this.sportsmanRepository = sportsmanRepository;
        this.organizerRepository = organizerRepository;
        this.sportFacilityRepository = sportFacilityRepository;
        this.clubMapper = clubMapper;
    }

    @PostConstruct
    private void setupMapper(){
        skipDTOField(CompetitionDTO::setSportsmen);
        skipEntityField(Competition::setSportsmen);

        skipDTOField(CompetitionDTO::setOrganizers);
        skipEntityField(Competition::setOrganizers);

        skipDTOField(CompetitionDTO::setSportFacilities);
        skipEntityField(Competition::setSportFacilities);

        skipDTOField(CompetitionDTO::setPrizeWinners);
        skipEntityField(Competition::setPrizeWinners);
    }

    protected void mapDTOToEntity(CompetitionDTO sourceDTO, Competition destination){

        List<Sportsman> sportsmen = new ArrayList<>();
        if (sourceDTO.getSportsmen()!=null)
            sourceDTO.getSportsmen().forEach(sportsmanDTO -> sportsmen.add(getEntityByIdOrThrow(sportsmanRepository, sportsmanDTO.getId())));
        destination.setSportsmen(sportsmen);

        List<Organizer> organizers = new ArrayList<>();
        if (sourceDTO.getOrganizers()!=null)
            sourceDTO.getOrganizers().forEach(organizerDTO -> organizers.add(getEntityByIdOrThrow(organizerRepository, organizerDTO.getId())));
        destination.setOrganizers(organizers);

        List<SportFacility> sportFacilities = new ArrayList<>();
        if (sourceDTO.getSportFacilities()!=null)
            sourceDTO.getSportFacilities().forEach( sportFacilityDTO -> sportFacilities.add(getEntityByIdOrThrow(sportFacilityRepository, sportFacilityDTO.getId())));
        destination.setSportFacilities(sportFacilities);

        List<Sportsman> prizeWinners = new ArrayList<>();
        if (sourceDTO.getPrizeWinners()!=null)
            sourceDTO.getPrizeWinners().forEach(sportsmanDTO -> prizeWinners.add(getEntityByIdOrThrow(sportsmanRepository, sportsmanDTO.getId())));
        destination.setPrizeWinners(prizeWinners);
    }

    @Override
    protected void mapEntityToDTO(Competition entity, CompetitionDTO DTO) {
        List<SportsmanDTO> sportsmenDTO = new ArrayList<>();
        for(Sportsman sportsman : entity.getSportsmen()){
            SportsmanDTO sportsmanDTO = new SportsmanDTO();
            sportsmanDTO.setId(sportsman.getId());
            sportsmanDTO.setName(sportsman.getName());
            sportsmanDTO.setClub(clubMapper.toDTO(sportsman.getClub()));
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

        List<SportFacilityDTO> sportFacilitiesDTO = new ArrayList<>();
        for(SportFacility sportFacility : entity.getSportFacilities()){

            SportFacilityDTO sportFacilityDTO = new SportFacilityDTO();
            sportFacilityDTO.setId(sportFacility.getId());

            Stadium stadium = sportFacility.getStadium();
            if(stadium !=null){
                StadiumDTO stadiumDTO = new StadiumDTO();
                stadiumDTO.setId(stadium.getId());
                stadiumDTO.setCapacity(stadium.getCapacity());
                sportFacilityDTO.setStadium(stadiumDTO);
            }

            IceArena iceArena = sportFacility.getIceArena();
            if(iceArena!=null){
                IceArenaDTO iceArenaDTO = new IceArenaDTO();
                iceArenaDTO.setId(iceArena.getId());
                iceArenaDTO.setSquare(iceArena.getSquare());
                sportFacilityDTO.setIceArena(iceArenaDTO);
            }

            Court court = sportFacility.getCourt();
            if (court!=null){
                CourtDTO courtDTO = new CourtDTO();
                courtDTO.setId(court.getId());
                courtDTO.setCoverageType(court.getCoverageType());
                sportFacilityDTO.setCourt(courtDTO);
            }

            VolleyballArena volleyballArena = sportFacility.getVolleyballArena();
            if (volleyballArena!=null){
                VolleyballArenaDTO volleyballArenaDTO = new VolleyballArenaDTO();
                volleyballArenaDTO.setId(volleyballArena.getId());
                volleyballArenaDTO.setNet_height(volleyballArena.getNet_height());
                volleyballArenaDTO.setNet_width(volleyballArena.getNet_width());
                sportFacilityDTO.setVolleyballArena(volleyballArenaDTO);
            }
            sportFacilitiesDTO.add(sportFacilityDTO);
        }
        DTO.setSportFacilities(sportFacilitiesDTO);

        List<SportsmanDTO> prizeWinnersDTO = new ArrayList<>();
        for(Sportsman sportsman : entity.getPrizeWinners()){
            SportsmanDTO prizeWinnerDTO = new SportsmanDTO();
            prizeWinnerDTO.setId(sportsman.getId());
            prizeWinnerDTO.setName(sportsman.getName());
            prizeWinnerDTO.setClub(clubMapper.toDTO(sportsman.getClub()));
            prizeWinnersDTO.add(prizeWinnerDTO);
        }
        DTO.setPrizeWinners(prizeWinnersDTO);
    }

}
