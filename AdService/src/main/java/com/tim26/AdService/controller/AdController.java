package com.tim26.AdService.controller;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.service.interfaces.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ads", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {

    @Autowired
    private AdService adService;


    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured from ad service");
        return "Hello svet from ad service";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AdDTO>> getAllAds(){

        List<AdDTO> ads = adService.findAll();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping(value = "/one/{id}")
    public ResponseEntity<AdDTO> getAd(@PathVariable String id){

        AdDTO ad = adService.findById(Long.parseLong(id));
        return new ResponseEntity<>(ad,HttpStatus.OK);

    }

    @GetMapping(value = "/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable String id){

        CarDTO dto = adService.findCarById(Long.parseLong(id));
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

    @PostMapping(value = "/save")
    public ResponseEntity<CreateAdDto> save(@RequestBody CreateAdDto createAdDto) {
        if(adService.save(createAdDto))
            return new ResponseEntity<>(createAdDto, HttpStatus.OK);

        return new ResponseEntity<>(createAdDto, HttpStatus.BAD_REQUEST);
    }

}
