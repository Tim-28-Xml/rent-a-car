package com.tim26.ChatService.service;

import com.tim26.ChatService.model.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
}
