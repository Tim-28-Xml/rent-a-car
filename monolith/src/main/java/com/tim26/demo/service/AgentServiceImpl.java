package com.tim26.demo.service;

import com.tim26.demo.model.Agent;
import com.tim26.demo.model.Permission;
import com.tim26.demo.repository.AgentRepository;
import com.tim26.demo.repository.PermissionRepository;
import com.tim26.demo.service.interfaces.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    private final List<String> agentPermissions = Arrays.asList("ROLE_AGENT", "CREATE_AD", "CREATE_REVIEW");

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
}
