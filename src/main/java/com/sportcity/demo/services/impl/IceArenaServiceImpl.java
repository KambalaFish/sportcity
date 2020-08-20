package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.IceArenaDTO;
import com.sportcity.demo.entities.IceArena;
import com.sportcity.demo.filters.IceArenaFilter;
import com.sportcity.demo.mappers.IMapperSF;
import com.sportcity.demo.repositories.IceArenaRepository;
import com.sportcity.demo.services.IceArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class IceArenaServiceImpl extends AbstractServiceSF<IceArena, IceArenaDTO, Integer> implements IceArenaService {

    private final IceArenaRepository repository;
    private final IMapperSF<IceArena, IceArenaDTO, Integer> mappper;

    @Autowired
    public IceArenaServiceImpl(
            IceArenaRepository repository,
            IMapperSF<IceArena, IceArenaDTO, Integer> mappper
    ){
        this.repository = repository;
        this.mappper = mappper;
    }


    @Override
    public Page<IceArenaDTO> getPageWithIceArenaById(Integer id, Pageable pageable) {
        return repository.findById(id, pageable).map(getMapper()::toDTO);
    }

    @Override
    public Page<IceArenaDTO> search(IceArenaFilter filter, Pageable pageable) {
        return repository.searchByFilter(filter.getMinSquare(), filter.getMaxSquare(), pageable).map(getMapper()::toDTO);
    }

    @Override
    protected JpaRepository<IceArena, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapperSF<IceArena, IceArenaDTO, Integer> getMapper() {
        return mappper;
    }
}
