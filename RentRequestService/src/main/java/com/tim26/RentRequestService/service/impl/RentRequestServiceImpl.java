package com.tim26.RentRequestService.service.impl;

import com.tim26.RentRequestService.controller.RentRequestController;
import com.tim26.RentRequestService.dto.AdDateRangeDTO;
import com.tim26.RentRequestService.dto.RentRequestDTO;
import com.tim26.RentRequestService.dto.ViewRequestDTO;
import com.tim26.RentRequestService.model.AdDateRange;
import com.tim26.RentRequestService.dto.ViewRequestDTO;
import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.RequestStatus;
import com.tim26.RentRequestService.model.User;
import com.tim26.RentRequestService.repository.RentRequestRepository;
import com.tim26.RentRequestService.repository.UserRepository;
import com.tim26.RentRequestService.service.RentRequestService;
import com.tim26.RentRequestService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    private static final Logger LOGGER= LoggerFactory.getLogger(RentRequestServiceImpl.class);

    @Autowired
    RentRequestRepository rentRequestRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


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

        for (RentRequest rentRequest : allRequests) {
            if (rentRequest.getRequestStatus().equals(RequestStatus.RESERVED) || rentRequest.getRequestStatus().equals(RequestStatus.PAID)) {

                String owner = rentRequest.getOwner().getUsername();
                String creator = rentRequest.getCreator().getUsername();


                if (!peopleforMsgs.contains(owner) && !owner.equals(user.getUsername())) {
                    peopleforMsgs.add(owner);
                }

                if(!peopleforMsgs.contains(creator) && !creator.equals(user.getUsername())){
                    peopleforMsgs.add(creator);
                }
            }
        }

        return peopleforMsgs;
    }

    public RentRequest pay(RentRequestController.ReqIdDTO id, Principal p) {
        RentRequest rentRequest = findById(Long.parseLong(id.reqId));
        User user = userService.findByUsername(p.getName());
        if(user == null)
            return null;

        if(rentRequest.getRequestStatus().equals(RequestStatus.RESERVED)){
            rentRequest.setRequestStatus(RequestStatus.PAID);
            save(rentRequest);

            if(cancelOtherPendingRequests(rentRequest))
                return rentRequest;
        }

        return null;

    }


    @Override
    public List<AdDateRangeDTO> getPaidRequestFromUser(Principal p) {

        List<RentRequest> requests = rentRequestRepository.findByCreator(userRepository.findById(p.getName()).get());
        List<AdDateRangeDTO> dtos =  new ArrayList<>();
        LocalDate now = LocalDate.now();

        for(RentRequest r: requests){
            if((r.getRequestStatus().equals(RequestStatus.PAID))) {
                for(AdDateRange x : r.getAdsWithDates()) {
                    if(now.isAfter(x.getEnd())) {
                        AdDateRangeDTO dto = new AdDateRangeDTO(x);
                        dtos.add(dto);
                    }
                }
            }
        }

        return dtos;
    }

    @Override
    public boolean approveRequest(String id) {
        RentRequest rentRequest = findById(Long.parseLong(id));
        if(rentRequest == null){
            return false;
        }

        if(rentRequest.getRequestStatus().equals(RequestStatus.PENDING)){
            rentRequest.setRequestStatus(RequestStatus.RESERVED);
            rentRequest.setReservationTime(LocalDateTime.now());
            save(rentRequest);
            LOGGER.info("Rent request with id {}, creator: {}, is approved by its owner: {}.", rentRequest.getId(),
                    rentRequest.getCreator().getUsername(), rentRequest.getOwner().getUsername());
            return true;
        } else {
            LOGGER.error("Rent request with id {}, creator: {}, cannot be approved by its owner: {} because its status is not pending.",
                    rentRequest.getId(), rentRequest.getCreator().getUsername(), rentRequest.getOwner().getUsername());
            return false;
        }

    }

    @Override
    public boolean declineRequest(String id) {
        RentRequest rentRequest = findById(Long.parseLong(id));
        if(rentRequest == null){
            return false;
        }

        rentRequest.setRequestStatus(RequestStatus.CANCELED);
        save(rentRequest);
        LOGGER.info("Rent request with id {}, creator: {}, is cancelled.", rentRequest.getId(),
                rentRequest.getCreator().getUsername());

        return true;
    }

    public List<RentRequest> getAvailableRequests(User user) {

        List<RentRequest> allRequests = findByOwner(user);

        if (allRequests == null)
            return null;

        List<RentRequest> pendingRequests = allRequests.stream().filter(r -> r.getRequestStatus() == RequestStatus.PENDING).collect(Collectors.toList());
        List<RentRequest> reservedRequests = allRequests.stream().filter(r -> r.getRequestStatus() == RequestStatus.RESERVED).collect(Collectors.toList());

        List<RentRequest> freshPending = new ArrayList<>(pendingRequests);
        for(RentRequest pending : pendingRequests){
            if(pending.getCreationTime().isBefore(LocalDateTime.now().minusHours(24))){
                pending.setRequestStatus(RequestStatus.CANCELED);
                save(pending);
                freshPending.remove(pending);
            }
        }

        for(RentRequest pRequest: freshPending){
            for(RentRequest rRequest: reservedRequests){
                for(AdDateRange pRange: pRequest.getAdsWithDates()){
                    for(AdDateRange rRange: rRequest.getAdsWithDates()){
                        if(pRange.getAd_id() == rRange.getAd_id()){
                            if(!(pRange.getStart().isAfter(rRange.getEnd()) || pRange.getEnd().isBefore(rRange.getStart()))){
                                if(allRequests.contains(pRequest))
                                    allRequests.remove(pRequest);
                            }
                        }
                    }
                }
            }
        }

        return allRequests;
    }

    @Override
    public boolean cancelOtherPendingRequests(RentRequest paidRequest) {

        List<RentRequest> allRequests = rentRequestRepository.findAll();

        if (allRequests == null)
            return false;

        List<RentRequest> pendingRequests = allRequests.stream().filter(r -> r.getRequestStatus() == RequestStatus.PENDING).collect(Collectors.toList());
        List<RentRequest> cancelledReqs = new ArrayList<>();

        for(RentRequest pRequest: pendingRequests){
                for(AdDateRange pRange: pRequest.getAdsWithDates()) {
                    for (AdDateRange range : paidRequest.getAdsWithDates()) {
                        if (pRange.getAd_id() == range.getAd_id()) {
                            if (!(pRange.getStart().isAfter(range.getEnd()) || pRange.getEnd().isBefore(range.getStart()))) {
                                if(!cancelledReqs.contains(pRequest))
                                    cancelledReqs.add(pRequest);
                            }
                        }
                    }
                }
        }

        cancelledReqs.forEach(req -> req.setRequestStatus(RequestStatus.CANCELED));
        rentRequestRepository.saveAll(cancelledReqs);
        LOGGER.info("Rent request with id {} is paid by {} and all other overlapping requests are cancelled.",
                paidRequest.getId(), paidRequest.getCreator().getUsername());

        return true;
    }

    @Override
    public List<ViewRequestDTO> getAllForEndUser(Principal principal) {
        List<ViewRequestDTO> viewRequestDTOS = new ArrayList<>();

        User user = userService.findByUsername(principal.getName());

        if(user == null)
            return null;

        List<RentRequest> rentRequests = findByCreator(user);

        for(RentRequest rentRequest: rentRequests){
            if(rentRequest.getRequestStatus().equals(RequestStatus.PENDING)){
                    if(rentRequest.getCreationTime().isBefore(LocalDateTime.now().minusHours(24))){
                        rentRequest.setRequestStatus(RequestStatus.CANCELED);
                        save(rentRequest);
                        LOGGER.info("Rent request with id {} is cancelled because it was created more than 24h ago. Creator: {}. Owner: {}.",
                                rentRequest.getId(), rentRequest.getCreator().getUsername(), rentRequest.getOwner().getUsername());
                    }
                } else if(rentRequest.getRequestStatus().equals(RequestStatus.RESERVED)){
                    if(rentRequest.getReservationTime() != null) {
                        if (rentRequest.getReservationTime().isBefore(LocalDateTime.now().minusHours(12))) {
                            rentRequest.setRequestStatus(RequestStatus.CANCELED);
                            save(rentRequest);
                            LOGGER.info("Rent request with id {} is cancelled because it was reserved and not paid for more than 12h. Creator: {}. Owner: {}.",
                                    rentRequest.getId(), rentRequest.getCreator().getUsername(), rentRequest.getOwner().getUsername());
                        }
                    }
            }
        }

        for(RentRequest rentRequest : rentRequests){
            viewRequestDTOS.add(new ViewRequestDTO(rentRequest));
        }

        return viewRequestDTOS;
    }
}
