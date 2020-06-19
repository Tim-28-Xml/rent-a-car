package com.tim26.demo.controller;

import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.RentAdDTO;
import com.tim26.demo.service.interfaces.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.io.IOException;
import java.security.Principal;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ads")
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

    @GetMapping(value = "/all")
    public ResponseEntity<List<AdDTO>> getAllAds(){

        List<AdDTO> ads = adService.findAll();
        return new ResponseEntity<>(ads,HttpStatus.OK);
    }

    @GetMapping(value = "/one/{id}")
    public ResponseEntity<AdDTO> getAd(@PathVariable Long id){

        AdDTO ad = adService.findById(id);
        return new ResponseEntity<>(ad,HttpStatus.OK);

    }

    @GetMapping(value = "/my-ads")
    public ResponseEntity<List<AdDTO>> getMyAds(Principal p){
        List<AdDTO> ads = adService.findMyAds(p.getName());
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping(value = "rent-creator")
    public ResponseEntity<String> rentAdByCreator(@RequestBody RentAdDTO rentAdDTO, Principal p){
        if(adService.rentByCreator(rentAdDTO, p)){
            return new ResponseEntity<>("Car is successfully rented!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Car cannot be rented!",HttpStatus.BAD_REQUEST);
        }

    }
}
