package com.tim26.AuthenticationService.service;

import com.tim26.AuthenticationService.dto.AgentDTO;
import com.tim26.AuthenticationService.model.Agent;
import com.tim26.AuthenticationService.model.Permission;
import com.tim26.AuthenticationService.repository.AgentRepository;
import com.tim26.AuthenticationService.repository.PermissionRepository;
import com.tim26.AuthenticationService.service.interfaces.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    private final List<String> agentPermissions = Arrays.asList("ROLE_AGENT", "CREATE_AD", "CREATE_REVIEW", "RENT",
            "SEND_MESSAGE", "CREATE_CODEBOOK", "CREATE_PRICELIST", "RENT_BY_CREATOR", "VIEW_MY_ADS");

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Agent save(Agent agent) {

        List<Permission> permissions = new ArrayList<>();
        for (String s: agentPermissions) {
            Permission permission = permissionRepository.findByName(s);
            permissions.add(permission);
        }

        agent.setPermissions(permissions);
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        return agentRepository.save(agent);
    }

    @Override
    public Agent findByMbr(int mbr) {
        return agentRepository.findByMbr(mbr);
    }

    @Override
    public List<AgentDTO> findAllAgents() {
        List<Agent> agents = agentRepository.findAll();
        List<AgentDTO> agentDTOS = new ArrayList<>();
        for (Agent agent: agents) {
            if(agent.isEnabled()) {
                AgentDTO agentDTO = new AgentDTO(agent);
                agentDTOS.add(agentDTO);
            }
        }
        return agentDTOS;
    }
}
