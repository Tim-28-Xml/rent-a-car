package com.tim26.RentRequestService.service;

import com.tim26.RentRequestService.model.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
}
