package com.tim26.demo.repository;

import com.tim26.demo.model.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {
    RentRequest findById(long id);

}
