package com.tim26.RentRequestService.service;

import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.User;

import java.security.Principal;
import java.util.List;

public interface RentRequestService {
    RentRequest findById(Long id);
    RentRequest save(RentRequest rentRequest);
    void deleteById(Long id);
    List<RentRequest> findByCreator(User user);
    List<RentRequest> findByOwner(User user);
    List<String> usersForMessages(Principal p);
}
