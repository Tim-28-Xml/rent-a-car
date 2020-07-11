package com.tim26.AuthenticationService.controller;

import com.tim26.AuthenticationService.dto.AgentDTO;
import com.tim26.AuthenticationService.dto.EndUserDTO;
import com.tim26.AuthenticationService.dto.PermissionsDTO;
import com.tim26.AuthenticationService.dto.UpdateUserDTO;
import com.tim26.AuthenticationService.model.Permission;
import com.tim26.AuthenticationService.model.User;
import com.tim26.AuthenticationService.service.interfaces.AgentService;
import com.tim26.AuthenticationService.service.interfaces.EndUserService;
import com.tim26.AuthenticationService.service.interfaces.UService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    EndUserService endUserService;

    @Autowired
    UService uService;

    @Autowired
    AgentService agentService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/endusers")
    public ResponseEntity<List<EndUserDTO>> getEndUsers(){

        List<EndUserDTO> users = endUserService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','AGENT')")
    @PutMapping(value = "/update")
    public boolean updateInfo(@RequestBody UpdateUserDTO dto){

        if(dto.getAgentDTO() != null){
            return   agentService.updateAgent(dto.getAgentDTO());
        }

        if(dto.getEndUserDTO() != null){
          return   endUserService.updateEndUser(dto.getEndUserDTO());
        }
        if(dto.getAdminDTO() != null){
            return uService.update(dto.getAdminDTO());
        }

        return false;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/permissions/{username}")
    public ResponseEntity<PermissionsDTO> getEndUserPermissions(@PathVariable String username){

        PermissionsDTO permissions = uService.getAllPermissions(username);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/remove/permission/{username}/{permission}")
    public ResponseEntity<PermissionsDTO> removeEndUserPermission(@PathVariable String username, @PathVariable String permission){

        if(permission == null){
            LOGGER.error("Failed to remove permission: Permission is null.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PermissionsDTO permissions = uService.removePermission(username,permission);

        LOGGER.info("Removed permission: user: {}, permission: {}.", username, permission);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/add/permission/{username}/{permission}")
    public ResponseEntity<PermissionsDTO> addEndUserPermission(@PathVariable String username, @PathVariable String permission){

        if(permission == null){
            LOGGER.error("Failed to enable permission: Permission is null.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PermissionsDTO permissions = uService.addPermission(username,permission);

        LOGGER.info("Enabled permission: user: {}, permission: {}.", username, permission);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/agents")
    public ResponseEntity<List<AgentDTO>> getAgents(){

        List<AgentDTO> users = agentService.findAllAgents();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/remove/{username}")
    public ResponseEntity removeUser(@PathVariable String username){

        if(uService.removeUser(username)){
            LOGGER.info("User: {} is removed by admin.", username);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            LOGGER.error("Failed to remove user: {} by admin.", username);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/confirm-account/{verificationCode}")
    public ResponseEntity confirmUserAccount(@PathVariable("verificationCode") String verificationCode) throws URISyntaxException {

        User user = uService.findVerificationCode(verificationCode);

        user.setEnabled(true);
        user.setActivated(true);
        uService.save(user);

        LOGGER.info("User: {} has confirmed his/hers account.", user.getUsername());

        URI newUri = new URI("https://localhost:3000/activated-account");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(newUri);

        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/requests")
    public ResponseEntity<List<EndUserDTO>> getAccountRequests(){

        List<EndUserDTO> requests = endUserService.findAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/requests/accept/{username}")
    public ResponseEntity acceptRegistrationRequest(@PathVariable String username){

        boolean accepted = uService.acceptAccount(username);
        if(accepted){
            LOGGER.info("Account with username: {} is accepted by admin.", username);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            LOGGER.error("Account with username: {} is not accepted by admin successfully.", username);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/requests/decline/{username}")
    public ResponseEntity declineRegistrationRequest(@PathVariable String username){

        boolean declined = uService.declineAccount(username);
        if(declined){
            LOGGER.info("Account with username: {} is declined by admin.", username);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            LOGGER.error("Account with username: {} is not declined by admin successfully.", username);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-id")
    public ResponseEntity<Long> getUserId(Principal p){

        return new ResponseEntity<>(uService.getUserId(p), HttpStatus.OK);
    }
}
