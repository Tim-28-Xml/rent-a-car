package com.tim26.demo.controller;

import com.tim26.demo.model.*;
import com.tim26.demo.security.TokenUtils;
import com.tim26.demo.security.auth.JwtAuthenticationRequest;
import com.tim26.demo.service.CustomUserDetailsService;
import com.tim26.demo.service.interfaces.AgentService;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private UService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/register/user")
    public ResponseEntity<?> createRegisterUser(@RequestBody EndUser user) {

        if(userService.findByUsername(user.getUsername()) != null ||
                userService.findByEmail(user.getEmail()) != null){
            return ResponseEntity.badRequest().build();
        }

        user.setEnabled(false);
        user.setActivated(false);
        endUserService.save(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/register/agent")
    public ResponseEntity<?> createRegisterAgent(@RequestBody Agent user) {

        if(userService.findByUsername(user.getUsername()) != null ||
                userService.findByEmail(user.getEmail()) != null ||
                agentService.findByMbr(user.getMbr()) != null){
            return ResponseEntity.badRequest().build();
        }
        user.setEnabled(true);
        agentService.save(user);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity<?> getRole(Principal p){

        User user = userService.findByUsername(p.getName());

        Collection<?> auth = user.getAuthorities();

        if(auth.size() == 0){
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(auth);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout(HttpServletRequest request) throws ServletException {
        request.logout();

        return ResponseEntity.ok().build();
    }


}
