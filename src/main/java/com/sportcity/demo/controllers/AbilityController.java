package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.services.AbilityService;
import com.sportcity.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ability")
public class AbilityController extends AbstractController<AbilityDTO, Integer>{

    private final AbilityService abilityService;

    @Autowired
    public AbilityController(AbilityService abilityService){
        this.abilityService = abilityService;
    }

    @Override
    protected Service<AbilityDTO, Integer> getService() {
        return abilityService;
    }

}
