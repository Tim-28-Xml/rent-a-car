package com.tim26.AdService.controller;

import com.tim26.AdService.model.BrandModels;
import com.tim26.AdService.model.Codebook;
import com.tim26.AdService.service.interfaces.CodebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/codebook")
public class CodebookController {

    private static final Logger LOGGER= LoggerFactory.getLogger(CodebookController.class);

    @Autowired
    CodebookService codebookService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Codebook> getCodebook(){
        if(codebookService.getFirstCodebook() != null) {
            LOGGER.info("Getting the codebook with id: {}", codebookService.getFirstCodebook().getId());
            return new ResponseEntity<>(codebookService.getFirstCodebook(), HttpStatus.OK);
        } else {
            LOGGER.warn("There is no present codebook.");
            return new ResponseEntity<>(codebookService.getFirstCodebook(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/fuel-types")
    public ResponseEntity<List<String>> getFuelTypes(){
        List<String> ft = codebookService.getFuelTypes();
        LOGGER.info("Getting all {} Fuel Types: {} ", ft.size(), ft);
        return new ResponseEntity<List<String>>(ft, HttpStatus.OK);
    }

    @GetMapping(value = "/transmission-types")
    public ResponseEntity<List<String>> getTransmissionTypes(){
        List<String> tp = codebookService.getTransmissionTypes();
        LOGGER.info("Getting all {} Transmission Types: {} ", tp.size(), tp);
        return new ResponseEntity<List<String>>(tp, HttpStatus.OK);
    }

    @GetMapping(value = "/car-classes")
    public ResponseEntity<List<String>> getCarClasses(){
        List<String> cc = codebookService.getCarClasses();
        LOGGER.info("Getting all{} Car Classes: {} ", cc.size(), cc);
        return new ResponseEntity<List<String>>(cc, HttpStatus.OK);
    }

    @GetMapping(value = "/brands")
    public ResponseEntity<List<BrandModels>> getBrands(){
        List<String> bms = new ArrayList<>();
        for(BrandModels bm : codebookService.getBrands()) {
            bms.add(bm.getBrand());
        }
        LOGGER.info("Getting all {} Brands: {} ", bms.size(), bms);
        return new ResponseEntity<List<BrandModels>>(codebookService.getBrands(), HttpStatus.OK);
    }

    @GetMapping(value = "/models-from-brand/{brand}")
    public ResponseEntity<List<String>> getModels(@PathVariable String brand){
        List<String> m = codebookService.getModelsFromBrand(brand);
        LOGGER.info("Getting all {} Models from Brand {}: {} ", m.size(), brand, m);
        return new ResponseEntity<List<String>>(m, HttpStatus.OK);
    }
}
