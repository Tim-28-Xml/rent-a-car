package com.tim26.RentRequestService.service;

import com.tim26.RentRequestService.controller.RentRequestController;
import com.tim26.RentRequestService.dto.AdDateRangeDTO;
import com.tim26.RentRequestService.dto.RentRequestDTO;
import com.tim26.RentRequestService.dto.ViewRequestDTO;
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
    boolean pay(RentRequestController.ReqIdDTO id, Principal p);
    List<AdDateRangeDTO> getPaidRequestFromUser(Principal p);

}
