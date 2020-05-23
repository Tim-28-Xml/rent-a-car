package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.Agent;

public interface AgentService {

    Agent save(Agent agent);
    Agent findByMbr(int mbr);
}
