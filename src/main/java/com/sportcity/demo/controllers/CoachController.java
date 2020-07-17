package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.services.CoachService;
import com.sportcity.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coach")
public class CoachController extends AbstractController<CoachDTO, Integer>{

    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService){
        this.coachService = coachService;
    }

    @Override
    protected Service<CoachDTO, Integer> getService() {
        return coachService;
    }
}
