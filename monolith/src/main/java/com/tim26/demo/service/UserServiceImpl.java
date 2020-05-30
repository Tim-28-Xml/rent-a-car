package com.tim26.demo.service;

import com.tim26.demo.dto.EndUserDTO;
import com.tim26.demo.dto.PermissionsDTO;
import com.tim26.demo.model.EndUser;
import com.tim26.demo.model.Permission;
import com.tim26.demo.model.User;
import com.tim26.demo.repository.UserRepository;
import com.tim26.demo.service.interfaces.EmailService;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionService permissionService;

    @Autowired
    EmailService emailService;

    @Autowired
    EndUserService endUserService;

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
    public PermissionsDTO getAllPermissions(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.isEnabled()) {
            return null;
        }

        List<String> permissions = new ArrayList<>();
        for (GrantedAuthority permission : user.getAuthorities()) {
            String p = permission.getAuthority();
            if (!p.contains("ROLE")) {
                permissions.add(p);
            }
        }

        PermissionsDTO permissionsDTO = new PermissionsDTO();
        permissionsDTO.setPermissions(permissions);
        permissionsDTO.setBlockedPermissions(user.getBlockedPermissions());
        return permissionsDTO;
    }

    @Override
    public PermissionsDTO removePermission(String username, String permission) {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.isEnabled()) {
            return null;
        }

        List<String> newList = new ArrayList<>();
        List<String> blockedPermissions = user.getBlockedPermissions();
        PermissionsDTO permissionsDTO = new PermissionsDTO();

        for (GrantedAuthority p : user.getAuthorities()) {
            if (p.getAuthority().equals(permission)) {
                user.getAuthorities().remove(p);
                user.getBlockedPermissions().add(permission);
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

        permissionsDTO.setPermissions(newList);
        permissionsDTO.setBlockedPermissions(blockedPermissions);

        return permissionsDTO;

    }

    @Override
    public PermissionsDTO addPermission(String username, String permission) {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.isEnabled()) {
            return null;
        }

        List<String> newList = new ArrayList<>();
        List<String> permissionsDTOlist = new ArrayList<>();
        List<Permission> newPermissions = new ArrayList<>();

        Permission prm = permissionService.findByName(permission);

        for (GrantedAuthority p : user.getAuthorities()) {
            newList.add(p.getAuthority());
        }
        newList.add(prm.getAuthority());

        for (String s: newList) {
            Permission pr = permissionService.findByName(s);
            newPermissions.add(pr);
            if(!s.contains("ROLE")){
                permissionsDTOlist.add(s);
            }
        }

        user.setPermissions(newPermissions);
        user.getBlockedPermissions().remove(permission);
        userRepository.save(user);

        PermissionsDTO permissionsDTO = new PermissionsDTO();
        permissionsDTO.setPermissions(permissionsDTOlist);
        permissionsDTO.setBlockedPermissions(user.getBlockedPermissions());

        return permissionsDTO;

    }

    @Override
    public boolean removeUser(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        user.setEnabled(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public User findVerificationCode(String findVerificationCode) {
        Optional<User> user = Optional.ofNullable(userRepository.findByVerificationCode(findVerificationCode));

        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    @Override
    public boolean acceptAccount(String username) {
        User user = userRepository.findByUsername(username);

        try {
            emailService.sendAcceptNotificaitionAsync(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    @Override
    public boolean declineAccount(String username) {

        User user = userRepository.findByUsername(username);
        user.setVerificationCode("-1");
        userRepository.save(user);

        try {
            emailService.sendDeclineNotificaitionAsync(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}
