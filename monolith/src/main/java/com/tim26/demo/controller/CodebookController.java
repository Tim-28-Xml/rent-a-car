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
}
