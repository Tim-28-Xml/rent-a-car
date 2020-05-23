package com.tim26.demo.controller;

import com.tim26.demo.model.Agent;
import com.tim26.demo.model.EndUser;
import com.tim26.demo.service.interfaces.AgentService;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private UService userService;

    @Autowired
    private AgentService agentService;


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/register/user")
    public ResponseEntity<?> createRegisterUser(@RequestBody EndUser user) {

        if(userService.findByUsername(user.getUsername()) != null ||
                userService.findByEmail(user.getEmail()) != null){
            return ResponseEntity.badRequest().build();
        }

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

        agentService.save(user);
        return ResponseEntity.ok().build();
    }


}
