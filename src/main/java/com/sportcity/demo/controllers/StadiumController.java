package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.StadiumDTO;
import com.sportcity.demo.entities.Stadium;
import com.sportcity.demo.filters.StadiumFilter;
import com.sportcity.demo.services.Service;
import com.sportcity.demo.services.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stadium")
public class StadiumController extends AbstractController<StadiumDTO, Integer>{

    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService){
        this.stadiumService = stadiumService;
    }

    @GetMapping("/pageWithStadiumById/{id}")
    public ResponseEntity<Page<StadiumDTO>> getPageWithStadiumById(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(stadiumService.getPageWithStadiumById(id, pageable));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<StadiumDTO>> search(@RequestBody StadiumFilter filter, Pageable pageable){
        return ResponseEntity.ok(stadiumService.search(filter, pageable));
    }

    @Override
    protected Service<StadiumDTO, Integer> getService() {
        return stadiumService;
    }
}
