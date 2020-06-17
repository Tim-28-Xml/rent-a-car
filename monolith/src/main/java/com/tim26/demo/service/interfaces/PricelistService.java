package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.CreatePricelistDto;

import java.security.Principal;

public interface PricelistService {
    boolean save(Principal p, CreatePricelistDto createPricelistDto);
}
