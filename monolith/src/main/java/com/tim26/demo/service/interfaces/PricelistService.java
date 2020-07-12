package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.CreatePricelistDto;
import com.tim26.demo.dto.PricelistDto;
import com.tim26.demo.model.PriceList;

import java.security.Principal;
import java.util.List;

public interface PricelistService {
    boolean save(Principal p, CreatePricelistDto createPricelistDto);
    List<CreatePricelistDto> findAll();
    PriceList findByName(String name);
    List<PricelistDto> findAllMine(Principal p);
    boolean delete(PricelistDto pricelistDto, Principal p);
}
