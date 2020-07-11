package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.*;
import com.tim26.AdService.model.Ad;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

public interface AdService {
    boolean save(CreateAdDto ad) throws SQLException;
    boolean save(Ad ad);
    List<AdDTO> findAll();
    AdDTO findById(long id);
    CarDTO findCarById(Long id);
    List<AdDTO> findMyAds(String username);
    boolean rentByCreator(RentAdDTO rentAdDTO, Principal p);
    Ad findAdById(Long id);
    boolean validateCreationData(CreateAdDto createAdDto);
    List<Ad> findByIds(List<Long> ids);
    Page<AdDTO> filterAds(FilterDTO filterDTO);
    boolean setRentDatesForAds(List<RentAdDTO> rentAdDTOS);
    Page<AdDTO> findAllPageable(int page);
    FilterParametersDTO getFilterParamteres();
}
