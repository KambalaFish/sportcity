package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.OrganizerDTO;
import com.sportcity.demo.services.OrganizerService;
import com.sportcity.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizer")
public class OrganizerController extends AbstractController<OrganizerDTO, Integer>{

    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService){
        this.organizerService = organizerService;
    }

    @GetMapping("/{id}/competitions")
    public ResponseEntity<Page<CompetitionDTO>> getCompetitions(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(organizerService.getCompetitionsOfTheOrganizer(id, pageable));
    }

    @PostMapping("/{id}/removeCompetition/{competitionId}")
    public ResponseEntity<Void> removeCompetitionFromOrganizer(@PathVariable Integer id, @PathVariable Integer competitionId){
        organizerService.removeLinkWithCompetition(id, competitionId);
        return ResponseEntity.ok().build();
    }

    @Override
    protected Service<OrganizerDTO, Integer> getService() {
        return organizerService;
    }
}
