package com.tim26.RentRequestService.service.impl;

import com.tim26.RentRequestService.controller.RentRequestController;
import com.tim26.RentRequestService.dto.ViewRequestDTO;
import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.RequestStatus;
import com.tim26.RentRequestService.model.User;
import com.tim26.RentRequestService.repository.RentRequestRepository;
import com.tim26.RentRequestService.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    RentRequestRepository rentRequestRepository;

    @Override
    public RentRequest findById(Long id) {
        return rentRequestRepository.findById(id).orElse(null);
    }

    @Override
    public RentRequest save(RentRequest rentRequest){
        return rentRequestRepository.save(rentRequest);
    }

    @Override
    public void deleteById(Long id){
        rentRequestRepository.deleteById(id);
    }

    @Override
    public List<RentRequest> findByCreator(User user){return rentRequestRepository.findByCreator(user);}

    @Override
    public List<RentRequest> findByOwner(User user){return rentRequestRepository.findByOwner(user);}

    @Override
    public boolean pay(RentRequestController.ReqIdDTO id, Principal p) {
        RentRequest rentRequest = findById(Long.parseLong(id.reqId));

        if(rentRequest.getRequestStatus().equals(RequestStatus.RESERVED)){
            rentRequest.setRequestStatus(RequestStatus.PAID);
            save(rentRequest);
            return true;
        } else {
            return false;
        }

    }


}
