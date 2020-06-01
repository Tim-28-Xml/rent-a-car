package com.tim26.demo.controller;

import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.service.interfaces.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('CREATE_AD')")
    public ResponseEntity<CreateAdDto> save(@RequestBody CreateAdDto createAdDto, Principal p) {
        if(adService.save(createAdDto, p))
            return new ResponseEntity<>(createAdDto, HttpStatus.OK);

        return new ResponseEntity<>(createAdDto, HttpStatus.BAD_REQUEST);
    }


}
