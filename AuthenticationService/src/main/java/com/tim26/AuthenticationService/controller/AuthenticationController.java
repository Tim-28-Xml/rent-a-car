package com.tim26.AuthenticationService.controller;

import com.netflix.discovery.converters.Auto;
import com.tim26.AuthenticationService.model.Agent;
import com.tim26.AuthenticationService.model.EndUser;
import com.tim26.AuthenticationService.model.PersonTokenState;
import com.tim26.AuthenticationService.model.User;
import com.tim26.AuthenticationService.security.TokenUtils;
import com.tim26.AuthenticationService.security.auth.JwtAuthenticationRequest;
import com.tim26.AuthenticationService.service.CustomUserDetailsService;
import com.tim26.AuthenticationService.service.interfaces.AgentService;
import com.tim26.AuthenticationService.service.interfaces.EndUserService;
import com.tim26.AuthenticationService.service.interfaces.UService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UService userService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    EndUserService endUserService;

    @Autowired
    AgentService agentService;

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
            LOGGER.error("Failed to log in: User with username: {} does not exist.", authenticationRequest.getUsername());
            return new ResponseEntity<>("User with this username does not exist!", HttpStatus.NOT_FOUND);
        }


        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User)userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        if(!user.isEnabled() || !user.isActivated()){
            LOGGER.warn("Failed to log in: Profile with this username: {} is not activated or enabled.", user.getUsername());
            return new ResponseEntity<>("Profile is not activated or enabled!", HttpStatus.BAD_REQUEST);
        }

        String jwt = tokenUtils.generateToken(user.getUsername(), user.getAuthorities());
        int expiresIn = tokenUtils.getExpiredIn();

        LOGGER.info("Successfully logged in user with username: {}", user.getUsername());
        return ResponseEntity.ok(new PersonTokenState(jwt, expiresIn, user.getUsername()));

    }

    @GetMapping(value = "/role")
    public ResponseEntity<?> getRole(Principal p){

        User user = userService.findByUsername(p.getName());

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        Collection<?> auth = user.getAuthorities();

        if(auth.size() == 0){
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(auth);
    }

    @GetMapping(value = "/verify/{token:.+}")
    public boolean verify(@PathVariable String token){
        String username = tokenUtils.getUsernameFromToken(token);
        User user = userService.findByUsername(username);

        if(username != null){
            return true;
        } else {
            return false;
        }
    }

    @GetMapping(value="/one/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        User user = userService.findById(Long.parseLong(id));

        if(user != null){
            return ResponseEntity.ok(user);
        }else {
            return  ResponseEntity.status(500).build();
        }

    }

    @PostMapping(consumes = "application/json", path = "/register/user")
    public ResponseEntity<?> createRegisterUser(@RequestBody EndUser user) {

        if(userService.findByUsername(user.getUsername()) != null ||
                userService.findByEmail(user.getEmail()) != null){
            return new ResponseEntity<>("Username or email is already taken!", HttpStatus.BAD_REQUEST);
        }

        String pass = user.getPassword();
        if(pass.length() < 10 || !userService.isPasswordValid(pass)){
            return new ResponseEntity<>("Password is not strong enough!", HttpStatus.BAD_REQUEST);
        }

        user.setEnabled(false);
        user.setActivated(false);
        endUserService.save(user);
        LOGGER.info("Created registration request for user with username: {}", user.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = "application/json", path = "/register/agent")
    public ResponseEntity<?> createRegisterAgent(@RequestBody Agent user) {

        if(userService.findByUsername(user.getUsername()) != null ||
                userService.findByEmail(user.getEmail()) != null ||
                agentService.findByMbr(user.getMbr()) != null){
            return new ResponseEntity<>("Username, id or email is already taken!", HttpStatus.BAD_REQUEST);
        }

        String pass = user.getPassword();
        if(pass.length() < 10 || !userService.isPasswordValid(pass)){
            return new ResponseEntity<>("Password is not strong enough!", HttpStatus.BAD_REQUEST);
        }

        user.setEnabled(true);
        user.setActivated(true);
        agentService.save(user);
        LOGGER.info("Registered agent with username: {}", user.getUsername());
        return ResponseEntity.ok().build();
    }
}
