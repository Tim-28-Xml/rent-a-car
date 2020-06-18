package com.tim26.AdService.service;

import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.model.PriceList;
import com.tim26.AdService.model.User;
import com.tim26.AdService.repository.PricelistRepository;
import com.tim26.AdService.service.interfaces.PricelistService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private UserService userService;

    @Autowired
    private PricelistRepository pricelistRepository;


    @Override
    public boolean save(Principal p, CreatePricelistDto createPricelistDto) {
        User user = userService.findByUsername(p.getName());
        if(user != null) {
            PriceList priceList = new PriceList();
            priceList.setCdwPrice(createPricelistDto.getCdwPrice());
            priceList.setDailyPrice(createPricelistDto.getDailyPrice());
            priceList.setPricePerExtraKm(createPricelistDto.getPricePerExtraKm());
            priceList.setName(createPricelistDto.getName());
            priceList.setUser(user);
            user.getPriceLists().add(priceList);

            priceList = pricelistRepository.save(priceList);
            return true;
        }
        return false;
    }
}
