package com.tim26.AdService.service.interfaces;


import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.model.PriceList;

import java.security.Principal;
import java.util.List;

public interface PricelistService {
    boolean save(Principal p, CreatePricelistDto createPricelistDto);
    List<CreatePricelistDto> findAll();
    PriceList findByName(String name);
}
