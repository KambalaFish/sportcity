package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.CourtDTO;
import com.sportcity.demo.entities.Court;
import com.sportcity.demo.mappers.IMapperSF;
import com.sportcity.demo.repositories.CourtRepository;
import com.sportcity.demo.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CourtServiceImpl extends AbstractServiceSF<Court, CourtDTO, Integer> implements CourtService {

    private final CourtRepository repository;
    private final IMapperSF<Court, CourtDTO, Integer> mapper;

    @Autowired
    public CourtServiceImpl(
            CourtRepository repository,
            IMapperSF<Court, CourtDTO, Integer> mapper
    ){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected JpaRepository<Court, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapperSF<Court, CourtDTO, Integer> getMapper() {
        return mapper;
    }

    @Override
    public Page<CourtDTO> getPageWithCourtById(Integer id, Pageable pageable) {
        return repository.findById(id, pageable).map(mapper::toDTO);
    }

}
