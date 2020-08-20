package com.sportcity.demo.controllers;

import com.sportcity.demo.dtos.IceArenaDTO;
import com.sportcity.demo.dtos.StadiumDTO;
import com.sportcity.demo.entities.IceArena;
import com.sportcity.demo.filters.IceArenaFilter;
import com.sportcity.demo.filters.StadiumFilter;
import com.sportcity.demo.services.IceArenaService;
import com.sportcity.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/search")
    public ResponseEntity<Page<IceArenaDTO>> search(@RequestBody IceArenaFilter filter, Pageable pageable){
        return ResponseEntity.ok(iceArenaService.search(filter, pageable));
    }

    @Override
    protected Service<IceArenaDTO, Integer> getService() {
        return iceArenaService;
    }
}
