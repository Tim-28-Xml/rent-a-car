package com.tim26.AdService.service.interfaces;


import com.tim26.AdService.dto.CreatePricelistDto;

import java.security.Principal;

public interface PricelistService {
    boolean save(Principal p, CreatePricelistDto createPricelistDto);
}
