package com.tim26.AuthenticationService.controller;

import com.netflix.discovery.converters.Auto;
import com.tim26.AuthenticationService.model.PersonTokenState;
import com.tim26.AuthenticationService.model.User;
import com.tim26.AuthenticationService.security.TokenUtils;
import com.tim26.AuthenticationService.security.auth.JwtAuthenticationRequest;
import com.tim26.AuthenticationService.service.CustomUserDetailsService;
import com.tim26.AuthenticationService.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UService userService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured");
        return "Hello svet from authentication service";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest){

        if(userService.findByUsername(authenticationRequest.getUsername()) == null){
            return ResponseEntity.notFound().build();
        }


        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User)userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        if(!user.isEnabled() || !user.isActivated()){
            return ResponseEntity.badRequest().build();
        }

        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new PersonTokenState(jwt, expiresIn));

    }

}
