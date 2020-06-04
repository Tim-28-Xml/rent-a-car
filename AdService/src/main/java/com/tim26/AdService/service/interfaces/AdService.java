package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;

import java.security.Principal;
import java.util.List;

public interface AdService {
    boolean save(AdDTO ad, Principal p);
    List<AdDTO> findAll();
    AdDTO findById(long id);
    CarDTO findCarById(Long id);
}
