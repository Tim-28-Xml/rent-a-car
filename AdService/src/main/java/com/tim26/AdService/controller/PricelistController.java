package com.tim26.AdService.controller;

import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.service.interfaces.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/pricelists", produces = MediaType.APPLICATION_JSON_VALUE)
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
