package com.tim26.AuthenticationService.service.interfaces;

import com.tim26.AuthenticationService.dto.AgentDTO;
import com.tim26.AuthenticationService.model.Agent;

import java.util.List;

public interface AgentService {
    Agent save(Agent agent);
    Agent findByMbr(int mbr);
    List<AgentDTO> findAllAgents();
    Agent findByUsername(String username);
    boolean updateAgent(AgentDTO agent);
}
