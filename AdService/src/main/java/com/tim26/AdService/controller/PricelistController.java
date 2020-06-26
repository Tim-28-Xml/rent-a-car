package com.tim26.AdService.controller;

import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.service.interfaces.PricelistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pricelists", produces = MediaType.APPLICATION_JSON_VALUE)
public class PricelistController {

    private static final Logger LOGGER= LoggerFactory.getLogger(PricelistController.class);

    @Autowired
    private PricelistService pricelistService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CreatePricelistDto>> getAll(){
        List<CreatePricelistDto> pricelistDtos = pricelistService.findAll();
        if(pricelistDtos == null)
            LOGGER.info("Getting all pricelists: []");
        else
            LOGGER.info("Getting all pricelists: {}", pricelistDtos);
        return new ResponseEntity<>(pricelistDtos,HttpStatus.OK);
    }


    @PostMapping(value = "/save")
    public ResponseEntity<CreatePricelistDto> save(@RequestBody CreatePricelistDto createPricelistDto, Principal p) {
        //if(pricelistService.save(p.getName(), createPricelistDto) != null)
        if(pricelistService.save(p, createPricelistDto)) {
            LOGGER.info("Create Pricelist - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
            return new ResponseEntity<>(createPricelistDto, HttpStatus.OK);
        }
        LOGGER.error("Create Pricelist Failed - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
        return new ResponseEntity<>(createPricelistDto, HttpStatus.BAD_REQUEST);
    }
}
