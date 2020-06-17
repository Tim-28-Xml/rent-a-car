package com.tim26.demo.service;

import com.tim26.demo.dto.CreatePricelistDto;
import com.tim26.demo.model.PriceList;
import com.tim26.demo.model.User;
import com.tim26.demo.repository.PricelistRepository;
import com.tim26.demo.service.interfaces.PricelistService;
import com.tim26.demo.service.interfaces.UService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private UService uService;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Override
    public boolean save(Principal p, CreatePricelistDto createPricelistDto) {
        User user = uService.findByUsername(p.getName());
        if(user != null) {
            PriceList priceList = new PriceList();
            priceList.setCdwPrice(createPricelistDto.getCdwPrice());
            priceList.setDailyPrice(createPricelistDto.getDailyPrice());
            priceList.setPricePerExtraKm(createPricelistDto.getPricePerExtraKm());
            priceList.setName(createPricelistDto.getName());
            priceList.setUser(user);

            priceList = pricelistRepository.save(priceList);
            return true;
        }
        return false;
    }
}
