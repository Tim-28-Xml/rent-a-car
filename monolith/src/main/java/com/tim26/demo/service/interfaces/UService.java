package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.User;
import com.tim26.demo.dto.EndUserDTO;

import java.util.List;

public interface UService {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    User save(User user);
    List<String> getAllPermissions(String username);
    List<String> removePermission(String username, String permission);
    List<String> addPermission(String username, String permission);

}
