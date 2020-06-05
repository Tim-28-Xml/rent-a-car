package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.model.User;

public interface UserService {
    User findById(long id);
    User save(User user);
}
