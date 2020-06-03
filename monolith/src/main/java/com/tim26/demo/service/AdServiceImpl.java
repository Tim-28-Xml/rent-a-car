package com.tim26.demo.service;

import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.model.*;
import com.tim26.demo.repository.AdRepository;
import com.tim26.demo.service.interfaces.AdService;
import com.tim26.demo.service.interfaces.AgentService;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.UService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import com.tim26.demo.dto.AdDTO;

import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private UService userService;

    @Autowired
    private AdRepository adRepository;

    @Override
    public boolean save(CreateAdDto ad, Principal p) {
        Agent agent = (Agent) userService.findByUsername(p.getName());
        if(agent != null) {

            Ad advertisment = new Ad();
            Car car = new Car();

            if(ad != null) {
                car.setBrand(ad.getBrand());
                car.setCarClass(ad.getCarClass());
                car.setModel(ad.getModel());
                car.setTransmission(ad.getTransmission());
                car.setFuel(ad.getFuel());
                car.setKm(ad.getKm());
                car.setKmLimit(ad.getKmLimit());
                car.setChildSeats(Integer.parseInt(ad.getChildSeats()));
                car.setCdw(ad.isCollision());
                List<byte[]> imgBytes = new ArrayList<byte[]>();

                for(String img : ad.getFiles()) {
                    byte[] imgByte = Base64.decodeBase64(img.getBytes());
                    imgBytes.add(imgByte);
                }
                car.setFiles(imgBytes);

                advertisment.setCar(car);

                advertisment.setRentDates(ad.getDates());

                agent.getAd().add(advertisment);
                advertisment.setUser(agent);

                try {
                    advertisment = adRepository.save(advertisment);
                    return  true;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<AdDTO> findAll() {
        List<Ad> allAds = adRepository.findAll();
        List<AdDTO> adDTOS = new ArrayList<>();

        for (Ad ad: allAds) {
            AdDTO adDTO = new AdDTO(ad);
            adDTOS.add(adDTO);
        }
        return adDTOS;
    }

    @Override
    public AdDTO findById(long id) {
        Ad ad = adRepository.findById(id);

        if(ad != null){
            AdDTO adDTO = new AdDTO(ad);
            return adDTO;
        }

        return null;
    }
}
