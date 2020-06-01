package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.model.Ad;

import java.security.Principal;
import java.util.List;

public interface AdService {
    boolean save(CreateAdDto ad, Principal p);
    List<Ad> getAllAds();
    List<AdDTO> findAll();
    AdDTO findById(long id);
}
