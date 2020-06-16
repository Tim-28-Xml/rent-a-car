package com.tim26.RentRequestService.service.impl;

import com.tim26.RentRequestService.model.User;
import com.tim26.RentRequestService.repository.UserRepository;
import com.tim26.RentRequestService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }


}
