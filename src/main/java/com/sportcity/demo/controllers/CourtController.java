package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.CoachDTO;
import com.sportcity.demo.dtos.CourtDTO;
import com.sportcity.demo.filters.CoachFilter;
import com.sportcity.demo.filters.CourtFilter;
import com.sportcity.demo.services.CourtService;
import com.sportcity.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/court")
public class CourtController extends AbstractController<CourtDTO, Integer> {

    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService){
        this.courtService = courtService;
    }

    @GetMapping("/pageWithCourtById/{id}")
    public ResponseEntity<Page<CourtDTO>> getPageWithCourtById(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(courtService.getPageWithCourtById(id, pageable));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CourtDTO>> search(@RequestBody CourtFilter courtFilter, Pageable pageable){
        return ResponseEntity.ok(courtService.search(courtFilter, pageable));
    }

    @Override
    protected Service<CourtDTO, Integer> getService() {
        return courtService;
    }
}
