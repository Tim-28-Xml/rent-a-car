package com.tim26.AdService.controller;


import com.tim26.AdService.model.Codebook;
import com.tim26.AdService.service.interfaces.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fuelType")
@PreAuthorize("hasRole('ADMIN')")
public class FuelTypesController {

    @Autowired
    CodebookService codebookService;

    public static class FuelTypeDTO {
        public String fuelType;
    }

    @PostMapping
    public ResponseEntity addFuelType(@RequestBody FuelTypeDTO fuelTypeDTO){

        Codebook codebook = codebookService.getFirstCodebook();

        if(codebook.getFuelTypes().contains(fuelTypeDTO.fuelType))
            return ResponseEntity.badRequest().build();

        codebook.getFuelTypes().add(fuelTypeDTO.fuelType);
        codebookService.save(codebook);

        return ResponseEntity.ok().build();
    }

}
