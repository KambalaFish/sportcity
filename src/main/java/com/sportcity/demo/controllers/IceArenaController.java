package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.IceArenaDTO;
import com.sportcity.demo.entities.IceArena;
import com.sportcity.demo.services.IceArenaService;
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
@RequestMapping("/iceArena")
public class IceArenaController extends AbstractController<IceArenaDTO, Integer>{

    private final IceArenaService iceArenaService;

    @Autowired
    public IceArenaController(IceArenaService iceArenaService){
        this.iceArenaService = iceArenaService;
    }

    @GetMapping("/pageWithIceArenaById/{id}")
    public ResponseEntity<Page<IceArenaDTO>> getPageWithIceArenaById(@PathVariable Integer id, Pageable pageable){
        return ResponseEntity.ok(iceArenaService.getPageWithIceArenaById(id, pageable));
    }

    @Override
    protected Service<IceArenaDTO, Integer> getService() {
        return iceArenaService;
    }
}
