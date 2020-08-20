package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.VolleyballArenaDTO;
import com.sportcity.demo.entities.VolleyballArena;
import com.sportcity.demo.filters.VolleyballArenaFilter;
import com.sportcity.demo.mappers.IMapperSF;
import com.sportcity.demo.repositories.VolleyballArenaRepository;
import com.sportcity.demo.services.VolleyballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VolleyballServiceImpl extends AbstractServiceSF<VolleyballArena, VolleyballArenaDTO, Integer> implements VolleyballService {

    private final VolleyballArenaRepository repository;
    private final IMapperSF<VolleyballArena, VolleyballArenaDTO, Integer> mapper;

    @Autowired
    public VolleyballServiceImpl(
            VolleyballArenaRepository repository,
            IMapperSF<VolleyballArena, VolleyballArenaDTO, Integer> mapper
    ){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<VolleyballArenaDTO> getPageWithVolleyballById(Integer id, Pageable pageable) {
        return repository.findById(id, pageable).map(getMapper()::toDTO);
    }

    @Override
    public Page<VolleyballArenaDTO> search(VolleyballArenaFilter filter, Pageable pageable) {
        return repository.searchByFilter(
                filter.getMinNetHeight(),
                filter.getMaxNetHeight(),
                filter.getMinNetWidth(),
                filter.getMaxNetWidth(),
                pageable
        ).map(getMapper()::toDTO);
    }

    @Override
    protected JpaRepository<VolleyballArena, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapperSF<VolleyballArena, VolleyballArenaDTO, Integer> getMapper() {
        return mapper;
    }
}
