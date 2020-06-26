package com.tim26.AdService.service;

import com.tim26.AdService.controller.PricelistController;
import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.model.PriceList;
import com.tim26.AdService.model.User;
import com.tim26.AdService.repository.PricelistRepository;
import com.tim26.AdService.service.interfaces.PricelistService;
import com.tim26.AdService.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PricelistServiceImpl implements PricelistService {

    private static final Logger LOGGER= LoggerFactory.getLogger(PricelistServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private ModelMapper modelMapper;

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

            try {

                priceList = pricelistRepository.save(priceList);
                return true;
            } catch (Exception e)
            {
                LOGGER.error("Failed to save pricelist - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} to the database \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
            }
        }
        LOGGER.error("Failed to create the pricelist with name - {} . User with {} username doesn't exist", createPricelistDto.getName(), p.getName());
        return false;
    }

    @Override
    public List<CreatePricelistDto> findAll() {
        List<PriceList> all = pricelistRepository.findAll();
        List<CreatePricelistDto> dtos = new ArrayList<>();

        for (PriceList p: all) {
            CreatePricelistDto dto = modelMapper.map(p,CreatePricelistDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public PriceList findByName(String name) {
        return pricelistRepository.findByName(name);
    }


}
