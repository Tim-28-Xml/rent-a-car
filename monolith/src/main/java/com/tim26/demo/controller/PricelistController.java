package com.tim26.demo.controller;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.CreatePricelistDto;
import com.tim26.demo.dto.PricelistDto;
import com.tim26.demo.service.interfaces.AdService;
import com.tim26.demo.service.interfaces.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/pricelists")
public class PricelistController {

    @Autowired
    private PricelistService pricelistService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CreatePricelistDto>> getAll(){

        List<CreatePricelistDto> pricelistDtos = pricelistService.findAll();
        return new ResponseEntity<>(pricelistDtos,HttpStatus.OK);
    }

    @GetMapping(value = "/all-mine")
    public ResponseEntity<List<PricelistDto>> getAllMine(Principal p){
        List<PricelistDto> pricelistDtos = pricelistService.findAllMine(p);
        return new ResponseEntity<>(pricelistDtos,HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<CreatePricelistDto> save(@RequestBody CreatePricelistDto createPricelistDto, Principal p) {
        if(pricelistService.save(p, createPricelistDto))
            return new ResponseEntity<>(createPricelistDto, HttpStatus.OK);
        return new ResponseEntity<>(createPricelistDto, HttpStatus.BAD_REQUEST);
    }
}
