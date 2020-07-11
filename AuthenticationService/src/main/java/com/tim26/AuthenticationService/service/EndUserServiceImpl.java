package com.tim26.AuthenticationService.service;

import com.tim26.AuthenticationService.dto.EndUserDTO;
import com.tim26.AuthenticationService.model.EndUser;
import com.tim26.AuthenticationService.model.Permission;
import com.tim26.AuthenticationService.repository.EndUserRepository;
import com.tim26.AuthenticationService.repository.PermissionRepository;
import com.tim26.AuthenticationService.service.interfaces.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean updateEndUser(EndUserDTO dto) {

        EndUser original = findByUsername(dto.getUsername());

        if(dto != null){

            if(dto.getFirstname() != "" && (!dto.getFirstname().equals(original.getFirstname()))) {
                original.setFirstname(dto.getFirstname());
            }

            if(dto.getLastname() != "" && (!dto.getLastname().equals(original.getLastname()))) {
                original.setLastname(dto.getLastname());
            }

            if(dto.getEmail() != "" && (!dto.getEmail() .equals(original.getEmail() ))) {
                original.setEmail(dto.getEmail() );
            }

            if(dto.getPassword() != "" && (!dto.getPassword().equals(original.getPassword()))) {
                original.setPassword(dto.getPassword() );
            }

            save(original);
            return true;
        }

        return false;
    }

}
