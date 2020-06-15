package com.tim26.RentRequestService.repository;

import com.tim26.RentRequestService.model.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {
}
