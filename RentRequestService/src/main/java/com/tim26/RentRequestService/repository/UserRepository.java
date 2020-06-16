package com.tim26.RentRequestService.repository;

import com.tim26.RentRequestService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
