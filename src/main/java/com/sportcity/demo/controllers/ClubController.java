package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.ClubDTO;
import com.sportcity.demo.filters.DateFilter;
import com.sportcity.demo.services.ClubService;
import com.sportcity.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController extends AbstractController<ClubDTO, Integer>{

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @Override
    protected Service<ClubDTO, Integer> getService() {
        return clubService;
    }

    @PostMapping("/{id}/numberOfSportsmanInTheClubDuringPeriod")
    ResponseEntity<Integer> getNumberOfSportsmenOfTheClubDuringPeriod(@PathVariable("id") Integer clubId, @RequestBody DateFilter filter){
        return ResponseEntity.ok(clubService.getNumberOfSportsmenOfTheClubDuringPeriod(clubId, filter));
    }

}
