package com.tim26.demo.service;

import com.tim26.demo.model.RentRequest;
import com.tim26.demo.repository.RentRequestRepository;
import com.tim26.demo.service.interfaces.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;

public class RentRequestServiceImpl implements RentRequestService {
    @Autowired
    RentRequestRepository rentRequestRepository;

    @Override
    public RentRequest findById(long id) {
        return rentRequestRepository.findById(id);
    }
}
