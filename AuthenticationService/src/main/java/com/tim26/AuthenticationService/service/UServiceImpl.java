package com.tim26.AuthenticationService.service;

import com.netflix.discovery.converters.Auto;
import com.tim26.AuthenticationService.model.User;
import com.tim26.AuthenticationService.repository.UserRepository;
import com.tim26.AuthenticationService.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UServiceImpl implements UService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
