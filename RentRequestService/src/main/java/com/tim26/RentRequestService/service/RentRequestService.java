package com.tim26.RentRequestService.service;

import com.tim26.RentRequestService.model.RentRequest;

public interface RentRequestService {
    RentRequest findById(Long id);
    RentRequest save(RentRequest rentRequest);
    void deleteById(Long id);
}
