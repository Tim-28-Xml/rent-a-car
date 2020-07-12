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
        createAdDto.setUsername(p.getName());
        if(adService.save(createAdDto))
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

    @GetMapping(value = "/is-my-ads/{id}")
    public ResponseEntity<?> checkIsMyAd(@PathVariable String id ,Principal p){
        boolean is = adService.isMyAd(p,Long.parseLong(id));
        return new ResponseEntity<>(is, HttpStatus.OK);
    }

    @GetMapping(value = "/my-ads-mileage")
    public ResponseEntity<List<AdDTO>> getMyAdsMileage(Principal p){
        List<AdDTO> ads = adService.findHighestMileage(p);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping(value = "/my-ads-rating")
    public ResponseEntity<List<AdDTO>> getMyAdsRating(Principal p){
        List<AdDTO> ads = adService.findHighestRating(p);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }


    @GetMapping(value = "/my-ads-reviews")
    public ResponseEntity<List<AdDTO>> getMyAdsReviews(Principal p){
        List<AdDTO> ads = adService.findMostReviews(p);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping(value = "/rent-creator")
    public ResponseEntity<String> rentAdByCreator(@RequestBody RentAdDTO rentAdDTO, Principal p){
        if(adService.rentByCreator(rentAdDTO, p)){
            return new ResponseEntity<>("Car is successfully rented!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Car cannot be rented!",HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody AdDTO adDTO, Principal p){
        if(adService.delete(adDTO, p)){
            return new ResponseEntity<>("Ad is successfully deleted!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ad cannot be deleted!",HttpStatus.BAD_REQUEST);
        }

    }


}
