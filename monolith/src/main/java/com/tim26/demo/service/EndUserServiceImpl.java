package com.tim26.demo.service;

import com.tim26.demo.model.EndUser;
import com.tim26.demo.model.Permission;
import com.tim26.demo.repository.EndUserRepository;
import com.tim26.demo.repository.PermissionRepository;
import com.tim26.demo.repository.UserRepository;
import com.tim26.demo.service.interfaces.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EndUserServiceImpl implements EndUserService {

    private final List<String> userPermissions = Arrays.asList("ROLE_USER", "CREATE_AD", "CREATE_REVIEW", "USE_CART", "ORDER");
    private final List<String> agentPermissions = Arrays.asList("ROLE_AGENT", "CREATE_AD", "CREATE_REVIEW");

    @Autowired
    private EndUserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public EndUser findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public EndUser findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public EndUser save(EndUser endUser){

        List<Permission> permissions = new ArrayList<>();

        for (String s: userPermissions) {
            Permission permission = permissionRepository.findByName(s);
            permissions.add(permission);
        }

        endUser.setPermissions(permissions);

        return userRepository.save(endUser);
    }


}
