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
@RequestMapping("/brand")
//@PreAuthorize("hasRole('ADMIN')")
public class CarBrandController {

    @Autowired
    BrandModelsService brandModelsService;

    @Autowired
    CodebookService codebookService;

    @PostMapping
    public ResponseEntity addBrand(@RequestBody BrandModels brandModels){

        Codebook codebook = codebookService.getFirstCodebook();

        for(BrandModels br : codebook.getBrandModels())
            if(br.getBrand().equals(brandModels.getBrand()))
                return ResponseEntity.badRequest().build();

        codebook.getBrandModels().add(brandModels);
        brandModelsService.save(brandModels);
        codebookService.save(codebook);

        return ResponseEntity.ok().build();
    }



}
