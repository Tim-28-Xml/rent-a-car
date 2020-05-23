package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.User;

public interface UService {

    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);

}
