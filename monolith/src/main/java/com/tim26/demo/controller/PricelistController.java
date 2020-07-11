package com.tim26.demo.controller;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.CreatePricelistDto;
import com.tim26.demo.dto.PricelistDto;
import com.tim26.demo.service.interfaces.AdService;
import com.tim26.demo.service.interfaces.PricelistService;
import com.tim26.demo.service.interfaces.UService;
//import com.tim26.demo.soap.PricelistClient;
//import com.xml.RentCar.wsdl.Ad;
//import com.xml.RentCar.wsdl.PricelistResponse;
//import com.xml.RentCar.wsdl.User;
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

    @Autowired
    private UService uService;

    /*@Autowired
    private PricelistClient client;*/

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
        if(pricelistService.save(p, createPricelistDto)) {
            //PricelistResponse response = client.postPricelist(createPricelistDto,p.getName());
            return new ResponseEntity<>(createPricelistDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(createPricelistDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody PricelistDto pricelistDto, Principal p){
        if(pricelistService.delete(pricelistDto, p)){
            return new ResponseEntity<>("Pricelist is successfully deleted!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pricelist cannot be deleted!",HttpStatus.BAD_REQUEST);
        }

    }
}
