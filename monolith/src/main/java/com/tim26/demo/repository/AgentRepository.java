package com.tim26.demo.repository;

import com.tim26.demo.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent save(Agent save);
    Agent findByMbr(int mbr);
}
