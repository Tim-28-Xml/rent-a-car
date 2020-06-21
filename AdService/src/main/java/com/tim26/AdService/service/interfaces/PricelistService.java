package com.tim26.AdService.service.interfaces;


import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.model.PriceList;

import java.security.Principal;
import java.util.List;

public interface PricelistService {
    PriceList save(String username, CreatePricelistDto createPricelistDto);
    List<CreatePricelistDto> findAll();
    PriceList findByName(String name);

}
