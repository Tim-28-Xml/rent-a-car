package com.tim26.demo.controller;

import com.tim26.demo.dto.EndUserDTO;
import com.tim26.demo.model.EndUser;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/endusers")
    public ResponseEntity<List<EndUserDTO>> getEndUsers(){

        List<EndUserDTO> users = endUserService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/enduser/permissions/{username}")
    public ResponseEntity<List<String>> getEndUserPermissions(@PathVariable String username){

        List<String> permissions = uService.getAllPermissions(username);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/remove/permission/{username}/{permission}")
    public ResponseEntity<List<String>> removeEndUserPermission(@PathVariable String username, @PathVariable String permission){

        List<String> permissions = uService.removePermission(username,permission);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/add/permission/{username}/{permission}")
    public ResponseEntity<List<String>> addEndUserPermission(@PathVariable String username, @PathVariable String permission){

        List<String> permissions = uService.addPermission(username,permission);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

}
