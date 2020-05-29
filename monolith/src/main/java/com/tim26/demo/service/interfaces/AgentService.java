package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.AgentDTO;
import com.tim26.demo.model.Agent;

import java.util.List;

public interface AgentService {

    Agent save(Agent agent);
    Agent findByMbr(int mbr);
    List<AgentDTO> findAllAgents();
}
