package com.sportcity.demo.controllers;


import com.sportcity.demo.dtos.AbilityDTO;
import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.CompetitionDTO;
import com.sportcity.demo.dtos.SportsmanDTO;
import com.sportcity.demo.services.Service;
import com.sportcity.demo.services.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sportsman")
public class SportsmanController extends AbstractController<SportsmanDTO, Integer>{

    private final SportsmanService sportsmanService;

    @Autowired
    public SportsmanController(SportsmanService sportsmanService){
        this.sportsmanService = sportsmanService;
    }

    @GetMapping("/{id}/abilities")
    public ResponseEntity<Page<AbilityDTO>> getAbilities(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(sportsmanService.getSportsmanAbilities(id, pageable));
    }

    @GetMapping("/{id}/coaches")
    public ResponseEntity<Page<CoachDTO>> getCoaches(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(sportsmanService.getCoachesOfTheSportsman(id, pageable));
    }

    @PostMapping("/{id}/removeCoach/{coachId}")
    public ResponseEntity<Void> removeCoachFromSportsman(@PathVariable Integer id, @PathVariable Integer coachId){
        sportsmanService.removeLinkWithCoach(id, coachId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/competitions")
    public ResponseEntity<Page<CompetitionDTO>> getCompetitions(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(sportsmanService.getCompetitionsOfTheSportsman(id, pageable));
    }

    @PostMapping("/{id}/removeCompetition/{competitionId}")
    public ResponseEntity<Void> removeCompetitionFromSportsman(@PathVariable Integer id, @PathVariable Integer competitionId){
        sportsmanService.removeLinkWithCompetition(id, competitionId);
        return ResponseEntity.ok().build();
    }

    @Override
    public Service<SportsmanDTO, Integer> getService(){
        return sportsmanService;
    }


}
