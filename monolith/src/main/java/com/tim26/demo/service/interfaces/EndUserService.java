package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.EndUser;

public interface EndUserService {

    EndUser findByEmail(String email);
    EndUser findByUsername(String username);
    EndUser save(EndUser endUser);
}
