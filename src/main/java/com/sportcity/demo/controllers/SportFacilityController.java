package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportFacilityDTO;
import com.sportcity.demo.services.Service;
import com.sportcity.demo.services.SportFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sportFacility")
public class SportFacilityController extends AbstractController<SportFacilityDTO, Integer>{

    private final SportFacilityService sportFacilityService;

    @Autowired
    public SportFacilityController(SportFacilityService sportFacilityService){
        this.sportFacilityService = sportFacilityService;
    }

    @GetMapping("/{id}/competitions")
    ResponseEntity<Page<CompetitionDTO>> getCompetitions(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(sportFacilityService.getCompetitionsOfTheSportFacility(id, pageable));
    }

    @GetMapping("/lastIdNumber")
    ResponseEntity<Integer> getLastIdNumber(){
        return ResponseEntity.ok(sportFacilityService.getLastIdNumber());
    }

    @PutMapping("/{id}/removeCompetition/{competitionId}")
    ResponseEntity<Void> removeCompetitionFromSportFacility(@PathVariable Integer id, @PathVariable Integer competitionId){
        sportFacilityService.removeLinkWithCompetition(id, competitionId);
        return ResponseEntity.ok().build();
    }


    @Override
    protected Service<SportFacilityDTO, Integer> getService() {
        return sportFacilityService;
    }
}
