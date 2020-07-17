package com.sportcity.demo.services.impl;


import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.entities.Coach;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.sportcity.demo.services.CoachService;

@Service
public class CoachServiceImpl extends AbstractService<Coach, CoachDTO, Integer> implements CoachService {

    private final CoachRepository repository;
    private final IMapper<Coach, CoachDTO, Integer> mapper;

    @Autowired
    public CoachServiceImpl(CoachRepository repository, IMapper<Coach, CoachDTO, Integer> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected JpaRepository<Coach, Integer> getRepository() {
        return repository;
    }

    @Override
    protected IMapper<Coach, CoachDTO, Integer> getMapper() {
        return mapper;
    }
}
