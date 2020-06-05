package com.tim26.AuthenticationService.service.interfaces;

import com.tim26.AuthenticationService.dto.PermissionsDTO;
import com.tim26.AuthenticationService.model.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    User findById(Long id);
    Long getUserId(Principal p);
}
