package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.ClubDTO;
import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Club;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.repositories.SportsmanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoachMapper extends AbstractMapper<Coach, CoachDTO, Integer>{

    private final SportsmanRepository sportsmanRepository;
    private final IMapper<Club, ClubDTO, Integer> clubMapper;
    @Autowired
    public CoachMapper(
            ModelMapper mapper,
            SportsmanRepository sportsmanRepository,
            IMapper<Club, ClubDTO, Integer> clubMapper
    ){
        super(mapper, Coach.class, CoachDTO.class);
        this.sportsmanRepository = sportsmanRepository;
        this.clubMapper = clubMapper;
    }

    @PostConstruct
    private void setupMapper(){
        skipEntityField(Coach::setSportsmen);
        skipDTOField(CoachDTO::setSportsmen);
    }

    protected void mapEntityToDTO(Coach entity, CoachDTO DTO) {
        List<SportsmanDTO> sportsmenDTO = new ArrayList<>();
        for(Sportsman sportsman : entity.getSportsmen()){
            SportsmanDTO sportsmanDTO = new SportsmanDTO();
            sportsmanDTO.setId(sportsman.getId());
            sportsmanDTO.setName(sportsman.getName());
            sportsmanDTO.setClub(clubMapper.toDTO(sportsman.getClub()));

            sportsmenDTO.add(sportsmanDTO);
        }
        DTO.setSportsmen(sportsmenDTO);
    }

    protected void mapDTOToEntity(CoachDTO DTO, Coach entity) {
        List<Sportsman> sportsmen = new ArrayList<>();
        for(SportsmanDTO sportsmanDTO : DTO.getSportsmen()){
            Sportsman sportsman = getEntityByIdOrThrow(sportsmanRepository, sportsmanDTO.getId());
            sportsmen.add(sportsman);
        }
        entity.setSportsmen(sportsmen);
    }
}
