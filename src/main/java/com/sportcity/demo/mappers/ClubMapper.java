package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.ClubDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.entities.Club;
import com.sportcity.demo.entities.Sportsman;
import com.sportcity.demo.entities.types.Sport;
import com.sportcity.demo.repositories.SportsmanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClubMapper extends AbstractMapper<Club, ClubDTO, Integer>{

    private final SportsmanRepository sportsmanRepository;
    private final IMapper<Sportsman, SportsmanDTO, Integer> sportsmanMapper;
    @Autowired
    public ClubMapper(
            ModelMapper mapper,
            SportsmanRepository sportsmanRepository,
            IMapper<Sportsman, SportsmanDTO, Integer> sportsmanMapper
    ){
        super(mapper, Club.class, ClubDTO.class);
        this.sportsmanRepository = sportsmanRepository;
        this.sportsmanMapper = sportsmanMapper;
    }

    @PostConstruct
    private void setupMapper(){
        skipDTOField(ClubDTO::setSportsmen);
        skipEntityField(Club::setSportsmen);
    }

    @Override
    protected void mapEntityToDTO(Club entity, ClubDTO dto) {
        super.mapEntityToDTO(entity, dto);
        List<SportsmanDTO> sportsmen = new ArrayList<>();
        entity.getSportsmen().forEach(sportsman -> sportsmen.add(sportsmanMapper.toDTO(sportsman)));
        dto.setSportsmen(sportsmen);
    }

    @Override
    protected void mapDTOToEntity(ClubDTO dto, Club entity) {
        super.mapDTOToEntity(dto, entity);
        List<Sportsman> sportsmen = new ArrayList<>();
        dto.getSportsmen().forEach(sportsmanDTO -> sportsmen.add(sportsmanRepository.findById(sportsmanDTO.getId()).get()));
        entity.setSportsmen(sportsmen);
    }
}
