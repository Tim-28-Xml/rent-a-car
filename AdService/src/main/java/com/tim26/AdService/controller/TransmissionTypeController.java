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
@RequestMapping("/transmissionType")
//@PreAuthorize("hasRole('ADMIN')")
public class TransmissionTypeController {
    @Autowired
    CodebookService codebookService;

    public static class TransmissionTypeDTO {
        public String transmissionType;
    }

    @PostMapping
    public ResponseEntity addTransmissionType(@RequestBody TransmissionTypeDTO transmissionTypeDTO){

        Codebook codebook = codebookService.getFirstCodebook();

        if(codebook.getCarClasses().contains(transmissionTypeDTO.transmissionType))
            return ResponseEntity.badRequest().build();

        codebook.getTransmissionTypes().add(transmissionTypeDTO.transmissionType);
        codebookService.save(codebook);

        return ResponseEntity.ok().build();
    }
}
