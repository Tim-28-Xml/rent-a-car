package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.dto.RentAdDTO;

import java.security.Principal;
import java.util.List;

public interface AdService {
    boolean save(CreateAdDto ad);
    List<AdDTO> findAll();
    AdDTO findById(long id);
    CarDTO findCarById(Long id);
    List<AdDTO> findMyAds(Long id);
    boolean rentByCreator(RentAdDTO rentAdDTO);
}
