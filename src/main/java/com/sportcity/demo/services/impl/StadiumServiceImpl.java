package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.StadiumDTO;
import com.sportcity.demo.entities.Stadium;
import com.sportcity.demo.filters.StadiumFilter;
import com.sportcity.demo.mappers.IMapperSF;
import com.sportcity.demo.repositories.StadiumRepository;
import com.sportcity.demo.services.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StadiumServiceImpl extends AbstractServiceSF<Stadium, StadiumDTO, Integer> implements StadiumService {

    private final StadiumRepository repository;
    private final IMapperSF<Stadium, StadiumDTO, Integer> mapper;

    @Autowired
    public StadiumServiceImpl(
            StadiumRepository repository,
            IMapperSF<Stadium, StadiumDTO, Integer> mapper
    ){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<StadiumDTO> getPageWithStadiumById(Integer id, Pageable pageable) {
        return repository.findById(id, pageable).map(mapper::toDTO);
    }

    @Override
    public Page<StadiumDTO> search(StadiumFilter filter, Pageable pageable) {
        return repository.searchByFilter(
                filter.getMinCapacity(),
                filter.getMaxCapacity(),
                pageable
                ).map(getMapper()::toDTO);
    }

    @Override
    protected JpaRepository<Stadium, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapperSF<Stadium, StadiumDTO, Integer> getMapper() {
        return mapper;
    }
}
