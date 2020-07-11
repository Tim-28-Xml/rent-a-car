package com.tim26.AdService.service.interfaces;


import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.model.PriceList;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

public interface PricelistService {
    boolean save(Principal p, CreatePricelistDto createPricelistDto) throws SQLException;
    List<CreatePricelistDto> findAll();
    PriceList findByName(String name);
    boolean validateCreationData(CreatePricelistDto dto);
    boolean delete(CreatePricelistDto priceList);
    PriceList findById(Long id);

}
