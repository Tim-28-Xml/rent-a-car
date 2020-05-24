package com.tim26.demo.controller;

import com.tim26.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<?> getRole(Principal p){

        return ResponseEntity.ok().build();

    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(Principal p){

        return ResponseEntity.ok().build();

    }

    @PreAuthorize("hasRole('AGENT')")
    @RequestMapping(value = "/agent", method = RequestMethod.GET)
    public ResponseEntity<?> getAgent(Principal p){

        return ResponseEntity.ok().build();

    }

    @PreAuthorize("hasRole('USER') and hasAuthority('CREATE_REVIEW')")
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ResponseEntity<?> comment(Principal p){

        return ResponseEntity.ok().build();

    }

    @PreAuthorize("hasRole('USER') and hasAuthority('ORDER')")
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity<?> order(Principal p){

        return ResponseEntity.ok().build();

    }

    @PreAuthorize("hasAnyRole('AGENT','USER') and hasAuthority('CREATE_AD')")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity<?> create(Principal p){

        return ResponseEntity.ok().build();

    }
}
