package com.tim26.demo.controller;

import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.CreatePricelistDto;
import com.tim26.demo.service.interfaces.AdService;
import com.tim26.demo.service.interfaces.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/pricelists")
public class PricelistController {

    @Autowired
    private PricelistService pricelistService;

    @PostMapping(value = "/save")
    public ResponseEntity<CreatePricelistDto> save(@RequestBody CreatePricelistDto createPricelistDto, Principal p) {
        if(pricelistService.save(p, createPricelistDto))
            return new ResponseEntity<>(createPricelistDto, HttpStatus.OK);
        return new ResponseEntity<>(createPricelistDto, HttpStatus.BAD_REQUEST);
    }
}
