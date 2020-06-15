package com.tim26.AuthenticationService.service;

import com.netflix.discovery.converters.Auto;
import com.tim26.AuthenticationService.dto.PermissionsDTO;
import com.tim26.AuthenticationService.model.Permission;
import com.tim26.AuthenticationService.model.User;
import com.tim26.AuthenticationService.repository.PermissionRepository;
import com.tim26.AuthenticationService.repository.UserRepository;
import com.tim26.AuthenticationService.service.interfaces.EmailService;
import com.tim26.AuthenticationService.service.interfaces.EndUserService;
import com.tim26.AuthenticationService.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UServiceImpl implements UService, UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    PermissionRepository permissionService;

    @Autowired
    EmailService emailService;

    @Autowired
    EndUserService endUserService;

    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public Long getUserId(Principal p) {
        User user = userRepository.findByUsername(p.getName());
        return user.getId();
    }

    @Override
    public boolean isPasswordValid(String password) {
        return textPattern.matcher(password).matches();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username '%s' was not found", username));
        } else {
            return user;
        }
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
        User user = userRepository.findByVerificationCode(findVerificationCode);

        if(user != null)
            return user;
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
