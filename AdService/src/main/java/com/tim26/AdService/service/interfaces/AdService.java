package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.dto.RentAdDTO;
import com.tim26.AdService.model.Ad;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

public interface AdService {
    boolean save(CreateAdDto ad, Principal p) throws SQLException;
    List<AdDTO> findAll();
    AdDTO findById(long id);
    CarDTO findCarById(Long id);
    List<AdDTO> findMyAds(String username);
    boolean rentByCreator(RentAdDTO rentAdDTO, Principal p);
    Ad findAdById(Long id);
    boolean validateCreationData(CreateAdDto createAdDto);
}
