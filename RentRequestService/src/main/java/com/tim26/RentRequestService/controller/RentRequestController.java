package com.tim26.RentRequestService.controller;

import com.tim26.RentRequestService.dto.RentRequestDTO;
import com.tim26.RentRequestService.dto.ViewRequestDTO;
import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.RequestStatus;
import com.tim26.RentRequestService.model.User;
import com.tim26.RentRequestService.service.RentRequestService;
import com.tim26.RentRequestService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentRequestController {

    @Value("${zuul-uri}")
    private String gatewayUri;

    @Autowired
    RentRequestService rentRequestService;

    @Autowired
    UserService userService;

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
    public ResponseEntity postRentRequest(@RequestBody RentRequestDTO rentRequestDTO, Principal principal){
        RentRequest rentRequest = new RentRequest(rentRequestDTO, principal.getName());

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

    @GetMapping("/myCreated")
    public List<ViewRequestDTO> getEndUserRequests(Principal principal){
        List<ViewRequestDTO> viewRequestDTOS = new ArrayList<>();

        User user = userService.findByUsername(principal.getName());

        if(user == null)
            return null;

        List<RentRequest> rentRequests = rentRequestService.findByCreator(user);

        if(rentRequests == null)
            return null;

        for(RentRequest rentRequest : rentRequests){
            viewRequestDTOS.add(new ViewRequestDTO(rentRequest));
        }

        return viewRequestDTOS;
    }

    @GetMapping("/myReceived")
    public List<ViewRequestDTO> getAgentRequests(Principal principal){
        List<ViewRequestDTO> viewRequestDTOS = new ArrayList<>();

        User user = userService.findByUsername(principal.getName());

        if(user == null)
            return null;

        List<RentRequest> rentRequests = rentRequestService.findByOwner(user);

        if(rentRequests == null)
            return null;

        for(RentRequest rentRequest : rentRequests){
            viewRequestDTOS.add(new ViewRequestDTO(rentRequest));
        }

        return viewRequestDTOS;
    }


    @GetMapping("/peoplechat")
    public ResponseEntity<List<String>> getUsersForChat(Principal principal) {
        List<String> people = rentRequestService.usersForMessages(principal);
        return new ResponseEntity(people, HttpStatus.OK);

    }
    

    }
