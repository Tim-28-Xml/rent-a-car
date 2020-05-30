package com.tim26.demo.controller;


import com.tim26.demo.model.Codebook;
import com.tim26.demo.service.interfaces.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/codebook")
public class CodebookController {

    @Autowired
    CodebookService codebookService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Codebook> getCodebook(){
        Codebook c = codebookService.getFirstCodebook();
        return new ResponseEntity<>(codebookService.getFirstCodebook(), HttpStatus.OK);
    }

    /*@GetMapping(value = "/fuel-types")
    @PreAuthorize("hasRole('CREATE_AD')")
    public ResponseEntity<List<String>> getFuelTypes(){
        return new ResponseEntity<List<String>>(codebookService.getFuelTypes(), HttpStatus.OK);
    }

    @GetMapping(value = "/transmission-types")
    @PreAuthorize("hasRole('CREATE_AD')")
    public ResponseEntity<List<String>> getTransmissionTypes(){
        return new ResponseEntity<List<String>>(codebookService.getTransmissionTypes(), HttpStatus.OK);
    }

    @GetMapping(value = "/car-classes")
    @PreAuthorize("hasRole('CREATE_AD')")
    public ResponseEntity<List<String>> getCarClasses(){
        return new ResponseEntity<List<String>>(codebookService.getCarClasses(), HttpStatus.OK);
    }*/
}
