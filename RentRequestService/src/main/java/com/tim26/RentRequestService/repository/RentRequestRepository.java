package com.tim26.RentRequestService.repository;

import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {
    List<RentRequest> findByCreator(User user);
    List<RentRequest> findByOwner(User user);
}
