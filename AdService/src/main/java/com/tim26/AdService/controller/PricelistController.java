package com.tim26.AdService.controller;

import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.service.interfaces.PricelistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
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
            LOGGER.info("Getting all pricelists: {}", pricelistDtos.size());
        return new ResponseEntity<>(pricelistDtos,HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('CREATE_PRICELIST')")
    @PostMapping(value = "/save")
    public ResponseEntity<CreatePricelistDto> save(@RequestBody CreatePricelistDto createPricelistDto, Principal p) throws SQLException {
        //if(pricelistService.save(p.getName(), createPricelistDto) != null)
        createPricelistDto.setUsername(p.getName());
        if(pricelistService.save(createPricelistDto)) {
            LOGGER.info("Response is 200 OK, CREATE PRICELIST - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
            return new ResponseEntity<>(createPricelistDto, HttpStatus.OK);
        }
        LOGGER.error("Response is 400 BAD REQUEST, Failed to CREATE PRICELIST  - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
        return new ResponseEntity<>(createPricelistDto, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('CREATE_PRICELIST')")
    @PostMapping(value = "/delete")
    public ResponseEntity<CreatePricelistDto> delete(@RequestBody CreatePricelistDto createPricelistDto) {
        if(pricelistService.delete(createPricelistDto))
            return new ResponseEntity<>(createPricelistDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(createPricelistDto, HttpStatus.BAD_REQUEST);
    }
}
