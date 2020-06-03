package com.tim26.AuthenticationService.repository;

import com.tim26.AuthenticationService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);
    User findByVerificationCode(String verificationCode);
}
