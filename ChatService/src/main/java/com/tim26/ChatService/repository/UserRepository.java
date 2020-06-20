package com.tim26.ChatService.repository;

import com.tim26.ChatService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User save(User user);
}
