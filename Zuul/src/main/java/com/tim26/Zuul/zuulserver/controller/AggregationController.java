package com.tim26.Zuul.zuulserver.controller;

import com.tim26.Zuul.zuulserver.client.AdsClient;
import com.tim26.Zuul.zuulserver.client.RentRequestClient;
import com.tim26.Zuul.zuulserver.dto.AdDTO;
import com.tim26.Zuul.zuulserver.dto.ReqAdDto;
import com.tim26.Zuul.zuulserver.dto.ReqIdDTO;
import com.tim26.Zuul.zuulserver.dto.ViewRequestDTO;
import com.tim26.Zuul.zuulserver.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AggregationController {

    @Autowired
    RentRequestService rentRequestService;

    @GetMapping("/user-requests")
    public ResponseEntity getUserRequests(@RequestHeader("Authorization") String token){
        return rentRequestService.getRequests(true, token);
    }

    @GetMapping("/agent-requests")
    public ResponseEntity getAgentRequests(@RequestHeader("Authorization") String token){
        return rentRequestService.getRequests(false, token);
    }

    @PostMapping("/api/rentrequest")
    public ResponseEntity postRentRequest(@RequestBody ViewRequestDTO dto, @RequestHeader("Authorization") String token){
        return rentRequestService.postRentRequest(dto, token);
    }

    @PostMapping("/pay")
    public ResponseEntity pay(@RequestBody ReqIdDTO reqIdDTO, @RequestHeader("Authorization") String token){
        return rentRequestService.pay(reqIdDTO, token);
    }
}
