package com.tim26.AuthenticationService.service.interfaces;

import com.tim26.AuthenticationService.dto.EndUserDTO;
import com.tim26.AuthenticationService.model.EndUser;

import java.util.List;

public interface EndUserService {

    EndUser findByEmail(String email);
    EndUser findByUsername(String username);
    List<EndUserDTO> findAll();
    List<EndUserDTO> findAllRequests();
    EndUser save(EndUser endUser);
    boolean checkPaidReports(String username, int num);
}
