package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.services.CompetitionService;
import com.sportcity.demo.services.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/competition")
public class CompetitionController extends AbstractController<CompetitionDTO, Integer>{

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService){
        this.competitionService = competitionService;
    }

    @GetMapping("/{id}/sportsmen")
    public ResponseEntity<Page<SportsmanDTO>> getSportsmen(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(competitionService.getSportsmenOfTheCompetition(id, pageable));
    }

    @PostMapping("/{id}/removeSportsman/{sportsmanId}")
    public ResponseEntity<Void> removeSportsmanFromCompetition(@PathVariable Integer id, @PathVariable Integer sportsmanId){
        competitionService.removeLinkWithSportsman(id, sportsmanId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/organizers")
    public ResponseEntity<Page<OrganizerDTO>> getOrganizers(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(competitionService.getOrganizersOfTheCompetition(id, pageable));
    }

    @PostMapping("/{id}/removeOrganizer/{organizerId}")
    public ResponseEntity<Void> removeOrganizerFromCompetition(@PathVariable Integer id, @PathVariable Integer organizerId){
        competitionService.removeLinkWithOrganizer(id, organizerId);
        return ResponseEntity.ok().build();
    }

    @Override
    protected Service<CompetitionDTO, Integer> getService() {
        return competitionService;
    }
}
