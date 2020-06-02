package com.tim26.demo.controller;

import com.tim26.demo.model.RentRequest;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rentRequest")
public class RentRequestController {

    @Autowired
    RentRequestService rentRequestService;

    @Autowired
    EndUserService endUserService;

    @PostMapping
    public ResponseEntity createRentRequest(@RequestBody RentRequest rentRequest, Principal principal){
        endUserService.findByUsername(principal.getName());

        return ResponseEntity.ok().build();
    }

}
