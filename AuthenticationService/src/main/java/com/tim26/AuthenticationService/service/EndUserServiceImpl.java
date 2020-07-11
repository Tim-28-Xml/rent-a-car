package com.tim26.AuthenticationService.service;

import com.tim26.AuthenticationService.dto.EndUserDTO;
import com.tim26.AuthenticationService.model.EndUser;
import com.tim26.AuthenticationService.model.Permission;
import com.tim26.AuthenticationService.model.User;
import com.tim26.AuthenticationService.repository.EndUserRepository;
import com.tim26.AuthenticationService.repository.PermissionRepository;
import com.tim26.AuthenticationService.service.interfaces.EndUserService;
import com.tim26.AuthenticationService.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EndUserServiceImpl implements EndUserService {

    private final List<String> userPermissions = Arrays.asList("ROLE_USER", "CREATE_AD", "CREATE_REVIEW", "USE_CART", "RENT",
            "ORDER", "SEND_MESSAGE", "CREATE_CODEBOOK", "CREATE_PRICELIST","RENT_BY_CREATOR", "VIEW_MY_ADS", "PAY");

    @Autowired
    private EndUserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UService uService;

    public EndUser findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public List<EndUserDTO> findAll() {
        List<EndUser> users = userRepository.findAll();
        List<EndUserDTO> usersDTO = new ArrayList<>();

        for (EndUser user: users) {
            if(user.isEnabled()) {
                EndUserDTO userDTO = new EndUserDTO(user);
                usersDTO.add(userDTO);
            }
        }
        return usersDTO;
    }

    @Override
    public List<EndUserDTO> findAllRequests() {
        List<EndUser> allUsers = userRepository.findAll();
        List<EndUserDTO> allRequests = new ArrayList<>();

        for (EndUser endUser: allUsers) {
            if(!endUser.isActivated() && endUser.getVerificationCode() == null) {
                EndUserDTO endUserDTO = new EndUserDTO(endUser);
                allRequests.add(endUserDTO);
            }
        }
        return allRequests;
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

        endUser.setPassword(passwordEncoder.encode(endUser.getPassword()));

        return userRepository.save(endUser);
    }

    @Override
    public boolean checkPaidReports(String username, int num) {
        User user = findByUsername(username);
        List<String> newList = new ArrayList<>();

        if(num == 0){
            for (GrantedAuthority p : user.getAuthorities()) {
                newList.add(p.getAuthority());
            }
            if(!newList.contains("ORDER")){
                uService.addPermission(username,"ORDER");
                return true;
            }
        } else {
            for (GrantedAuthority p : user.getAuthorities()) {
                newList.add(p.getAuthority());
            }
            if(newList.contains("ORDER")){
                uService.removePermission(username,"ORDER");
                return true;
            }
        }
        return false;
    }

}
