package com.tim26.Zuul.zuulserver.service;

import com.tim26.Zuul.zuulserver.dto.ReqIdDTO;
import com.tim26.Zuul.zuulserver.dto.ViewRequestDTO;
import org.springframework.http.ResponseEntity;


public interface RentRequestService {
    ResponseEntity getRequests(boolean isEndUser, String token);
    ResponseEntity postRentRequest(ViewRequestDTO dto, String token);
    ResponseEntity pay(ReqIdDTO reqIdDTO, String token);
}
