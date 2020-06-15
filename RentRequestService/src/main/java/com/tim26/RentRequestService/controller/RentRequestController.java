package com.tim26.RentRequestService.controller;

import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentRequestController {

    @Value("${zuul-uri}")
    private String gatewayUri;

    @Autowired
    RentRequestService rentRequestService;

    @GetMapping("{id}")
    public ResponseEntity getRentRequest(@PathVariable String id) {

        RentRequest rentRequest;

        try{
            rentRequest = rentRequestService.findById(Long.parseLong(id));
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().build();
        }

        if(rentRequest == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(rentRequest);
    }

    @PostMapping
    public ResponseEntity postRentRequest(RentRequest rentRequest){
        if(rentRequestService.save(rentRequest) == null){
            return ResponseEntity.badRequest().build();
        } else {
            URI uri = URI.create(gatewayUri + "/api/" + rentRequest.getId());
            return ResponseEntity.created(uri).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRentRequest(@PathVariable String id){
        try{
             rentRequestService.deleteById(Long.parseLong(id));
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
