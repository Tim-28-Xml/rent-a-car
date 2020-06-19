package com.tim26.demo.service;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.CreatePricelistDto;
import com.tim26.demo.model.Ad;
import com.tim26.demo.model.PriceList;
import com.tim26.demo.model.User;
import com.tim26.demo.repository.PricelistRepository;
import com.tim26.demo.service.interfaces.PricelistService;
import com.tim26.demo.service.interfaces.UService;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private UService uService;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private ModelMapper modelMapper;

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
