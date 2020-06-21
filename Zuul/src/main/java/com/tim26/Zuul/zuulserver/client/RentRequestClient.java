package com.tim26.Zuul.zuulserver.client;

import com.tim26.Zuul.zuulserver.controller.AggregationController;
import com.tim26.Zuul.zuulserver.dto.ReqIdDTO;
import com.tim26.Zuul.zuulserver.dto.ViewRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("rentrequestservice")
public interface RentRequestClient {
    @GetMapping("/api/myCreated")
    List<ViewRequestDTO> getEndUserRequests(@RequestHeader("Authorization") String token);

    @GetMapping("/api/myReceived")
    List<ViewRequestDTO> getAgentRequests(@RequestHeader("Authorization") String token);

    @PostMapping("/api")
    ResponseEntity postRentRequest(@RequestBody ViewRequestDTO dto, @RequestHeader("Authorization") String token);

    @PostMapping("/api/pay")
    ViewRequestDTO pay(@RequestBody ReqIdDTO reqIdDTO, @RequestHeader("Authorization") String token);
}
