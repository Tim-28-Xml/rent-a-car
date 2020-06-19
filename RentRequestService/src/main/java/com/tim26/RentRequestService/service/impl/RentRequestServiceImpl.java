package com.tim26.RentRequestService.service.impl;

import com.tim26.RentRequestService.controller.RentRequestController;
import com.tim26.RentRequestService.dto.ViewRequestDTO;
import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.RequestStatus;
import com.tim26.RentRequestService.model.User;
import com.tim26.RentRequestService.repository.RentRequestRepository;
import com.tim26.RentRequestService.service.RentRequestService;
import com.tim26.RentRequestService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    RentRequestRepository rentRequestRepository;

    @Autowired
    UserService userService;

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
    public List<String> usersForMessages(Principal p) {
        User user = userService.findByUsername(p.getName());
        List<RentRequest> allCreator = findByCreator(user);
        List<RentRequest> allOwner = findByOwner(user);
        Set<RentRequest> set = new LinkedHashSet<>(allCreator);
        set.addAll(allOwner);
        List<RentRequest> allRequests = new ArrayList<>(set);

        List<String> peopleforMsgs = new ArrayList<>();

        for(RentRequest rentRequest: allRequests){
            if(rentRequest.getRequestStatus().equals(RequestStatus.RESERVED)) {
                if (!peopleforMsgs.contains(rentRequest.getOwner().getUsername()) &&
                        !peopleforMsgs.contains(rentRequest.getCreator().getUsername()) &&
                        !rentRequest.getOwner().equals(user.getUsername()) &&
                        !rentRequest.getCreator().equals(user.getUsername())) {
                    peopleforMsgs.add(rentRequest.getOwner().getUsername());
                }
            }
        }

        return peopleforMsgs;
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
