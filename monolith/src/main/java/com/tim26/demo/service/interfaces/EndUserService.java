package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.EndUserDTO;
import com.tim26.demo.model.EndUser;

import java.util.List;

public interface EndUserService {

    EndUser findByEmail(String email);
    EndUser findByUsername(String username);
    List<EndUserDTO> findAll();
    EndUser save(EndUser endUser);
}
