package com.tim26.AuthenticationService.repository;

import com.tim26.AuthenticationService.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent save(Agent save);
    Agent findByMbr(int mbr);
    List<Agent> findAll();
}
