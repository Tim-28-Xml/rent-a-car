package com.tim26.RentRequestService.controller;

import com.tim26.RentRequestService.dto.AdDateRangeDTO;
import com.tim26.RentRequestService.dto.RentRequestDTO;
import com.tim26.RentRequestService.dto.ViewRequestDTO;
import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.RequestStatus;
import com.tim26.RentRequestService.model.User;
import com.tim26.RentRequestService.service.RentRequestService;
import com.tim26.RentRequestService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class  RentRequestController {

    @Value("${zuul-uri}")
    private String gatewayUri;

    private static final Logger LOGGER= LoggerFactory.getLogger(RentRequestController.class);

    @Autowired
    RentRequestService rentRequestService;

    @Autowired
    UserService userService;

    public static class ReqIdDTO {
        public String reqId;
    }

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

    @PreAuthorize("hasAuthority('ORDER')")
    @PostMapping
    public ResponseEntity postRentRequest(@RequestBody RentRequestDTO rentRequestDTO, Principal principal){
        RentRequest rentRequest = new RentRequest(rentRequestDTO, principal.getName());

        if(rentRequestService.save(rentRequest) == null){
            LOGGER.error("Failed creating rent request with id: {} by {}.", rentRequest.getId(), rentRequest.getCreator().getUsername());
            return ResponseEntity.badRequest().build();
        } else {
            LOGGER.info("Created rent request by {}. Owner: {}. Id: {}.", rentRequest.getCreator().getUsername(), rentRequest.getOwner().getUsername(), rentRequest.getId());
            URI uri = URI.create(gatewayUri + "/api/" + rentRequest.getId());
            return ResponseEntity.created(uri).build();
        }
    }

    @PreAuthorize("hasAuthority('RENT')")
    @DeleteMapping("{id}")
    public ResponseEntity deleteRentRequest(@PathVariable String id){
        try{
             rentRequestService.deleteById(Long.parseLong(id));
            LOGGER.info("Deleted rent request with id: {}", id);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @GetMapping("/myCreated")
    public List<ViewRequestDTO> getEndUserRequests(Principal principal){

        return rentRequestService.getAllForEndUser(principal);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @GetMapping("/myReceived")
    public List<ViewRequestDTO> getAgentRequests(Principal principal){
        List<ViewRequestDTO> viewRequestDTOS = new ArrayList<>();

        User user = userService.findByUsername(principal.getName());

        if(user == null)
            return null;

        List<RentRequest> rentRequests = new ArrayList<>();

        rentRequests = rentRequestService.getAvailableRequests(user);

        for(RentRequest rentRequest : rentRequests){
            viewRequestDTOS.add(new ViewRequestDTO(rentRequest));
        }
        LOGGER.info("User {} has {} received and available rent requests.", principal.getName(), viewRequestDTOS.size());

        return viewRequestDTOS;
    }

    
    @GetMapping("/peoplechat")
    public ResponseEntity<List<String>> getUsersForChat(Principal principal) {
        List<String> people = rentRequestService.usersForMessages(principal);
        LOGGER.info("User {} has {} people to send message to.", principal.getName(), people.size());
        return new ResponseEntity(people, HttpStatus.OK);

    }
    

    @PreAuthorize("hasAuthority('PAY')")
    @PostMapping("/pay")
    public ViewRequestDTO payRentRequest(@RequestBody ReqIdDTO reqIdDTO, Principal principal){
        RentRequest rentRequest = rentRequestService.pay(reqIdDTO, principal);

        if(rentRequest != null){
            LOGGER.info("User {} has paid for rent request with id: {}.", principal.getName(), rentRequest.getId());
            return new ViewRequestDTO(rentRequest);
        } else {
            LOGGER.info("Failed to pay for rent request: {} by user: {}.", reqIdDTO.reqId, principal.getName());
            return null;
        }
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my-paid-finished")
    public ResponseEntity<List<AdDateRangeDTO>> getUserPaidFinishedReq(Principal principal){

        List<AdDateRangeDTO> dtos = rentRequestService.getPaidRequestFromUser(principal);

        if(dtos.size() != 0){
            return new ResponseEntity(dtos, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAuthority('RENT')")
    @PostMapping("/approve")
    public ResponseEntity<?> approveRequest(@RequestBody ReqIdDTO reqId){
        boolean isApproved = rentRequestService.approveRequest(reqId.reqId);
        if(isApproved){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('RENT','ORDER')")
    @PostMapping("/decline")
    public ResponseEntity<?> declineRequest(@RequestBody ReqIdDTO reqId){
        boolean isDeclined = rentRequestService.declineRequest(reqId.reqId);
        if(isDeclined){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }




}
