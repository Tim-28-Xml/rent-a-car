package com.tim26.demo.controller;

import com.tim26.demo.dto.AgentDTO;
import com.tim26.demo.dto.EndUserDTO;
import com.tim26.demo.dto.PermissionsDTO;
import com.tim26.demo.model.EndUser;
import com.tim26.demo.model.Permission;
import com.tim26.demo.service.interfaces.AgentService;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
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

}
