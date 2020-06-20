package com.tim26.demo.controller;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.CreateReportDto;
import com.tim26.demo.dto.RentedCarDto;
import com.tim26.demo.model.Ad;
import com.tim26.demo.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping(value = "/save")
    public ResponseEntity<CreateReportDto> save(@RequestBody CreateReportDto createReportDto, Principal p) {
        if(reportService.save(createReportDto, p))
            return new ResponseEntity<>(createReportDto, HttpStatus.OK);

        return new ResponseEntity<>(createReportDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/my-rented-ads", method = RequestMethod.GET)
    public ResponseEntity<List<RentedCarDto>> getAllMyRentedAds(Principal p){
        List<RentedCarDto> ads = reportService.findAllRentedCars(p);
        return new ResponseEntity<>(ads,HttpStatus.OK);
    }
}
