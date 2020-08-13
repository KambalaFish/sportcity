package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.*;
import com.sportcity.demo.filters.CompetitionFilter;
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

    @GetMapping("/{id}/sportFacilities")
    public ResponseEntity<Page<SportFacilityDTO>> getSportFacilities(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(competitionService.getSportFacilitiesOfTheCompetition(id, pageable));
    }

    @PostMapping("/{id}/removeSportFacility/{sportFacilityId}")
    public ResponseEntity<Void> removeSportFacilityFromCompetition(@PathVariable Integer id, @PathVariable Integer sportFacilityId){
        competitionService.removeLinkWithSportFacility(id, sportFacilityId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/courts")
    public ResponseEntity<Page<CourtDTO>> getCourts(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(competitionService.getCourtsOfTheCompetition(id, pageable));
    }

    @GetMapping("/{id}/stadiums")
    public ResponseEntity<Page<StadiumDTO>> getStadiums(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(competitionService.getStadiumsOfTheCompetition(id, pageable));
    }

    @GetMapping("/{id}/iceArenas")
    public ResponseEntity<Page<IceArenaDTO>> getIceArenas(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(competitionService.getIceArenasOfTheCompetition(id, pageable));
    }

    @GetMapping("/{id}/volleyballArenas")
    public ResponseEntity<Page<VolleyballArenaDTO>> getVolleyballArenas(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(competitionService.getVolleyballArenasOfTheCompetition(id, pageable));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CompetitionDTO>> search(@RequestBody CompetitionFilter filter, Pageable pageable){
        return ResponseEntity.ok(competitionService.search(filter, pageable));
    }

    @Override
    protected Service<CompetitionDTO, Integer> getService() {
        return competitionService;
    }
}
