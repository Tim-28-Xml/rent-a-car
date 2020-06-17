package com.tim26.Zuul.zuulserver.service;

import org.springframework.http.ResponseEntity;


public interface RentRequestService {
    ResponseEntity getRequests(boolean isEndUser, String token);
}
