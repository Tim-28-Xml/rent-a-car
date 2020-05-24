package com.tim26.demo.controller;

import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(value = "api/ads")
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<CreateAdDto> createAd(@RequestBody CreateAdDto createAdDto) throws SQLException {
        if(adService.createAd(createAdDto))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
