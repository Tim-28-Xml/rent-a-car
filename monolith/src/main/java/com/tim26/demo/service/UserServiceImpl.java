package com.tim26.demo.service;

import com.tim26.demo.model.Permission;
import com.tim26.demo.model.User;
import com.tim26.demo.repository.UserRepository;
import com.tim26.demo.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionService permissionService;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<String> getAllPermissions(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        List<String> permissions = new ArrayList<>();
        for (GrantedAuthority permission : user.getAuthorities()) {
            String p = permission.getAuthority();
            if (!p.contains("ROLE")) {
                permissions.add(p);
            }
        }
        return permissions;
    }

    @Override
    public List<String> removePermission(String username, String permission) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        List<String> newList = new ArrayList<>();

        for (GrantedAuthority p : user.getAuthorities()) {
            if (p.getAuthority().equals(permission)) {
                user.getAuthorities().remove(p);
                userRepository.save(user);
                break;
            }
        }

        for (GrantedAuthority pr : user.getAuthorities()) {
            String p = pr.getAuthority();
            if (!p.contains("ROLE")) {
                newList.add(p);
            }
        }

        return newList;

    }

    @Override
    public List<String> addPermission(String username, String permission) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        List<String> newList = new ArrayList<>();
        List<Permission> newPermissions = new ArrayList<>();

        Permission prm = permissionService.findByName(permission);

        for (GrantedAuthority p : user.getAuthorities()) {
            newList.add(p.getAuthority());
        }
        newList.add(prm.getAuthority());

        for (String s: newList) {
            Permission pr = permissionService.findByName(s);
            newPermissions.add(pr);
        }
        user.setPermissions(newPermissions);
        userRepository.save(user);

        return newList;

    }


}
