package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.*;
import com.sportcity.demo.entities.*;
import com.sportcity.demo.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SportFacilityMapper extends AbstractMapperSF<SportFacility, SportFacilityDTO, Integer>{


    private final CompetitionRepository competitionRepository;

    private final VolleyballArenaRepository volleyballArenaRepository;

    private final StadiumRepository stadiumRepository;

    private final IceArenaRepository iceArenaRepository;

    private final CourtRepository courtRepository;

    @Autowired
    protected SportFacilityMapper(
            ModelMapper mapper,
            CompetitionRepository competitionRepository,
            VolleyballArenaRepository volleyballArenaRepository,
            StadiumRepository stadiumRepository,
            IceArenaRepository iceArenaRepository,
            CourtRepository courtRepository
            ){
        super(mapper, SportFacility.class, SportFacilityDTO.class);
        this.competitionRepository = competitionRepository;
        this.volleyballArenaRepository = volleyballArenaRepository;
        this.stadiumRepository = stadiumRepository;
        this.iceArenaRepository = iceArenaRepository;
        this.courtRepository = courtRepository;
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(SportFacility::setCompetitions);
        skipEntityField(SportFacility::setVolleyballArena);
        skipEntityField(SportFacility::setCourt);
        skipEntityField(SportFacility::setIceArena);
        skipEntityField(SportFacility::setStadium);

        skipDTOField(SportFacilityDTO::setCompetitions);
        skipDTOField(SportFacilityDTO::setVolleyballArena);
        skipDTOField(SportFacilityDTO::setCourt);
        skipDTOField(SportFacilityDTO::setIceArena);
        skipDTOField(SportFacilityDTO::setStadium);
    }

    @Override
    protected void mapDTOToEntity(SportFacilityDTO dto, SportFacility entity) {
        List<Competition> competitions = new ArrayList<>();
        if (dto.getCompetitions()!=null)
            dto.getCompetitions().forEach(competitionDTO -> {
                if (competitionRepository.findById(competitionDTO.getId()).isPresent())
                    competitions.add(competitionRepository.findById(competitionDTO.getId()).get());
            });
        entity.setCompetitions(competitions);


        if (dto.getVolleyballArena()!=null){
            //if (volleyballArenaRepository.findById(dto.getId()).isPresent())
                //entity.setVolleyballArena(getEntityByIdOrThrow(volleyballArenaRepository, dto.getId()));
            //else {
                VolleyballArena volleyballArena = new VolleyballArena();
                VolleyballArenaDTO volleyballArenaDTO = dto.getVolleyballArena();

                volleyballArena.setId(volleyballArenaDTO.getId());
                volleyballArena.setNet_height(volleyballArenaDTO.getNet_height());
                volleyballArena.setNet_width(volleyballArenaDTO.getNet_width());
                volleyballArena.setSportFacility(entity);

                entity.setVolleyballArena(volleyballArena);
            //}
        }

        if(dto.getCourt()!=null){
                Court court = new Court();
                CourtDTO courtDTO = dto.getCourt();

                court.setId(courtDTO.getId());
                court.setCoverageType(courtDTO.getCoverageType());
                court.setSportFacility(entity);

                entity.setCourt(court);
            //}
        }

        if (dto.getIceArena()!=null){
            //if (iceArenaRepository.findById(dto.getId()).isPresent())
                //entity.setIceArena(getEntityByIdOrThrow(iceArenaRepository, dto.getId()));
            //else {
                IceArena iceArena = new IceArena();
                IceArenaDTO iceArenaDTO = dto.getIceArena();

                iceArena.setId(iceArenaDTO.getId());
                iceArena.setSquare(iceArenaDTO.getSquare());
                iceArena.setSportFacility(entity);

                entity.setIceArena(iceArena);
            //}
        }

        if(dto.getStadium()!=null){
            //if (stadiumRepository.findById(dto.getId()).isPresent())
                //entity.setStadium(getEntityByIdOrThrow(stadiumRepository, dto.getId()));
            //else {
                Stadium stadium = new Stadium();
                StadiumDTO stadiumDTO = dto.getStadium();

                stadium.setId(stadiumDTO.getId());
                stadium.setCapacity(stadiumDTO.getCapacity());
                stadium.setSportFacility(entity);

                entity.setStadium(stadium);
            //}
        }

    }

    @Override
    protected void mapEntityToDTO(SportFacility entity, SportFacilityDTO dto){
        List<CompetitionDTO> competitions = new ArrayList<>();
        for(Competition competition : entity.getCompetitions()){
            CompetitionDTO competitionDTO = new CompetitionDTO();
            competitionDTO.setId(competition.getId());
            competitionDTO.setName(competition.getName());
            competitionDTO.setBeginningDate(competition.getBeginningDate());
            competitionDTO.setFinishDate(competition.getFinishDate());
            competitionDTO.setSport(competition.getSport());
            competitions.add(competitionDTO);
        }
        dto.setCompetitions(competitions);

        if (entity.getVolleyballArena() != null){
            VolleyballArena volleyballArena = entity.getVolleyballArena();

            VolleyballArenaDTO volleyballArenaDTO = new VolleyballArenaDTO();
            volleyballArenaDTO.setId(volleyballArena.getId());
            volleyballArenaDTO.setNet_height(volleyballArena.getNet_height());
            volleyballArenaDTO.setNet_width(volleyballArena.getNet_width());
            //volleyballArenaDTO.setSportFacilityDTO(dto);

            dto.setVolleyballArena(volleyballArenaDTO);
        }

        if (entity.getCourt() != null){
            Court court = entity.getCourt();

            CourtDTO courtDTO = new CourtDTO();
            courtDTO.setId(court.getId());
            courtDTO.setCoverageType(court.getCoverageType());
            //courtDTO.setSportFacilityDTO(dto);

            dto.setCourt(courtDTO);
        }

        if (entity.getIceArena() != null){
            IceArena iceArena = entity.getIceArena();

            IceArenaDTO iceArenaDTO = new IceArenaDTO();
            iceArenaDTO.setId(iceArena.getId());
            iceArenaDTO.setSquare(iceArena.getSquare());
            //iceArenaDTO.setSportFacilityDTO(dto);

            dto.setIceArena(iceArenaDTO);
        }

        if (entity.getStadium() != null){
            Stadium stadium = entity.getStadium();

            StadiumDTO stadiumDTO = new StadiumDTO();
            stadiumDTO.setId(stadium.getId());
            stadiumDTO.setCapacity(stadium.getCapacity());
            //stadiumDTO.setSportFacilityDTO(dto);

            dto.setStadium(stadiumDTO);
        }

    }

}
