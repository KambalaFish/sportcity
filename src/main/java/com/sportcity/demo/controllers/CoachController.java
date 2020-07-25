package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.services.CoachService;
import com.sportcity.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}/sportsmen")
    public ResponseEntity<Page<SportsmanDTO>> getSportsmen(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(coachService.getSportsmenOfTheCoach(id, pageable));
    }

    @Override
    protected Service<CoachDTO, Integer> getService() {
        return coachService;
    }
}