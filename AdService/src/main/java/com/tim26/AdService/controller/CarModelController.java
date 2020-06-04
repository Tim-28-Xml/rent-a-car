package com.tim26.AdService.controller;

import com.tim26.AdService.model.BrandModels;
import com.tim26.AdService.model.Codebook;
import com.tim26.AdService.service.interfaces.BrandModelsService;
import com.tim26.AdService.service.interfaces.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model")
@PreAuthorize("hasRole('ADMIN')")
public class CarModelController {

    @Autowired
    CodebookService codebookService;

    @Autowired
    BrandModelsService brandModelsService;

    public static class CarModelDTO{
        public String brand;
        public String model;
    }

    @PostMapping
    public ResponseEntity addCarModel(@RequestBody CarModelDTO carModelDTO){
        Codebook codebook = codebookService.getFirstCodebook();
        BrandModels brandModels = null;

        for (BrandModels br : codebook.getBrandModels()){
            if(br.getBrand().equals(carModelDTO.brand))
                brandModels = br;
        }

        if(brandModels == null)
            return ResponseEntity.badRequest().build();

        if(brandModels.getModels().contains(carModelDTO.model))
            return ResponseEntity.badRequest().build();
        else
            brandModels.getModels().add(carModelDTO.model);

        return ResponseEntity.ok().build();
    }
}
