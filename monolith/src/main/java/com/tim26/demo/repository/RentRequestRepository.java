package com.tim26.demo.repository;

import com.tim26.demo.model.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {

    Optional<RentRequest> findById(Long id);
    RentRequest save(RentRequest rentRequest);
}
