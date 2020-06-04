package com.tim26.AdService.controller;

import com.tim26.AdService.model.Codebook;
import com.tim26.AdService.service.interfaces.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carClass")
//@PreAuthorize("hasRole('ADMIN')")
public class CarClassController {
    @Autowired
    CodebookService codebookService;

    public static class CarClassDTO {
        public String carClass;
    }

    @PostMapping
    public ResponseEntity addCarClass(@RequestBody CarClassDTO carClassDTO){
        Codebook codebook = codebookService.getFirstCodebook();

        if(codebook.getCarClasses().contains(carClassDTO.carClass))
            return ResponseEntity.badRequest().build();

        codebook.getCarClasses().add(carClassDTO.carClass);
        codebookService.save(codebook);

        return ResponseEntity.ok().build();
    }
}
