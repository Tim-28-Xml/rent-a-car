package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.PermissionsDTO;
import com.tim26.demo.model.User;
import com.tim26.demo.dto.EndUserDTO;

import java.util.List;

public interface UService {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    User save(User user);
    PermissionsDTO getAllPermissions(String username);
    PermissionsDTO removePermission(String username, String permission);
    PermissionsDTO addPermission(String username, String permission);
    boolean removeUser(String username);
    //User update(User user);
    User findVerificationCode(String findVerificationCode);
    boolean acceptAccount(String username);
    boolean declineAccount(String username);

}
