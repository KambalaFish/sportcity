package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.VolleyballArenaDTO;
import com.sportcity.demo.services.Service;
import com.sportcity.demo.services.VolleyballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volleyballArena")
public class VolleyballArenaController extends AbstractController<VolleyballArenaDTO, Integer>{

    private final VolleyballService volleyballService;

    @Autowired
    public VolleyballArenaController(VolleyballService volleyballService){
        this.volleyballService = volleyballService;
    }

    @GetMapping("/pageWithVolleyballArenaById/{id}")
    public ResponseEntity<Page<VolleyballArenaDTO>> getPageWithVolleyballArenaById(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(volleyballService.getPageWithVolleyballById(id, pageable));
    }

    @Override
    protected Service<VolleyballArenaDTO, Integer> getService() {
        return volleyballService;
    }
}
