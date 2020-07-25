package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.entities.Ability;
import com.sportcity.demo.mappers.IMapper;
import com.sportcity.demo.repositories.AbilityRepository;
import com.sportcity.demo.services.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AbilityServiceImpl extends AbstractService<Ability, AbilityDTO, Integer> implements AbilityService {
    private final AbilityRepository repository;
    private final IMapper<Ability, AbilityDTO, Integer> mapper;

    @Autowired
    public AbilityServiceImpl(AbilityRepository abilityRepository, IMapper<Ability, AbilityDTO, Integer> mapper){
        this.repository = abilityRepository;
        this.mapper = mapper;
    }

    @Override
    protected JpaRepository<Ability, Integer> getRepository(){
        return repository;
    }

    @Override
    protected IMapper<Ability, AbilityDTO, Integer> getMapper(){
        return mapper;
    }

}
