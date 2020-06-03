package com.tim26.AuthenticationService.service.interfaces;

import com.tim26.AuthenticationService.model.User;
import org.springframework.stereotype.Service;

public interface UService {

    User findByUsername(String username);
}
