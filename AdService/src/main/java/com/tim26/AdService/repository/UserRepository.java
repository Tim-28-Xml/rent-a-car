package com.tim26.AdService.repository;

import com.tim26.AdService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Long save(Long id);
    Optional<User> findById(Long id);
}
