package com.tim26.demo.repository;

import com.tim26.demo.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndUserRepository extends JpaRepository<EndUser, Long> {

    public EndUser findByUsername(String username);
    public EndUser findByEmail(String email);
    public EndUser save(EndUser endUser);
}
