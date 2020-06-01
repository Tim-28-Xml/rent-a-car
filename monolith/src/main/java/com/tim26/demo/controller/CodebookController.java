package com.tim26.demo.controller;


import com.tim26.demo.model.BrandModels;
import com.tim26.demo.model.Codebook;
import com.tim26.demo.service.interfaces.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/codebook")
public class CodebookController {

    @Autowired
    CodebookService codebookService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Codebook> getCodebook(){
        return new ResponseEntity<>(codebookService.getFirstCodebook(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_AD')")
    @GetMapping(value = "/fuel-types")
    public ResponseEntity<List<String>> getFuelTypes(){
        return new ResponseEntity<List<String>>(codebookService.getFuelTypes(), HttpStatus.OK);
    }

    @GetMapping(value = "/transmission-types")
    @PreAuthorize("hasAuthority('CREATE_AD')")
    public ResponseEntity<List<String>> getTransmissionTypes(){
        return new ResponseEntity<List<String>>(codebookService.getTransmissionTypes(), HttpStatus.OK);
    }

    @GetMapping(value = "/car-classes")
    @PreAuthorize("hasAuthority('CREATE_AD')")
    public ResponseEntity<List<String>> getCarClasses(){
        return new ResponseEntity<List<String>>(codebookService.getCarClasses(), HttpStatus.OK);
    }

    @GetMapping(value = "/brands")
    @PreAuthorize("hasAuthority('CREATE_AD')")
    public ResponseEntity<List<BrandModels>> getBrands(){
        return new ResponseEntity<List<BrandModels>>(codebookService.getBrands(), HttpStatus.OK);
    }

    @GetMapping(value = "/models-from-brand/{brand}")
    @PreAuthorize("hasAuthority('CREATE_AD')")
    public ResponseEntity<List<String>> getModels(@PathVariable String brand){
        return new ResponseEntity<List<String>>(codebookService.getModelsFromBrand(brand), HttpStatus.OK);
    }
}
