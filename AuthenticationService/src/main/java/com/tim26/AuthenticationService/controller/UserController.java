package com.tim26.AuthenticationService.controller;

import com.tim26.AuthenticationService.dto.AgentDTO;
import com.tim26.AuthenticationService.dto.EndUserDTO;
import com.tim26.AuthenticationService.dto.PermissionsDTO;
import com.tim26.AuthenticationService.model.User;
import com.tim26.AuthenticationService.service.interfaces.AgentService;
import com.tim26.AuthenticationService.service.interfaces.EndUserService;
import com.tim26.AuthenticationService.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/permissions/{username}")
    public ResponseEntity<PermissionsDTO> getEndUserPermissions(@PathVariable String username){

        PermissionsDTO permissions = uService.getAllPermissions(username);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/remove/permission/{username}/{permission}")
    public ResponseEntity<PermissionsDTO> removeEndUserPermission(@PathVariable String username, @PathVariable String permission){

        PermissionsDTO permissions = uService.removePermission(username,permission);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/add/permission/{username}/{permission}")
    public ResponseEntity<PermissionsDTO> addEndUserPermission(@PathVariable String username, @PathVariable String permission){

        PermissionsDTO permissions = uService.addPermission(username,permission);
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
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/confirm-account/{verificationCode}")
    public ResponseEntity confirmUserAccount(@PathVariable("verificationCode") String verificationCode) throws URISyntaxException {

        User user = uService.findVerificationCode(verificationCode);

        user.setEnabled(true);
        user.setActivated(true);
        uService.save(user);

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
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/requests/decline/{username}")
    public ResponseEntity declineRegistrationRequest(@PathVariable String username){

        boolean declined = uService.declineAccount(username);
        if(declined){
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
