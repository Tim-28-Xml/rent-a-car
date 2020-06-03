package com.tim26.AuthenticationService.repository;

import com.tim26.AuthenticationService.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EndUserRepository extends JpaRepository<EndUser, Long> {

    public List<EndUser> findAll();
    public EndUser findByUsername(String username);
    public EndUser findByEmail(String email);
    public EndUser save(EndUser endUser);

}
