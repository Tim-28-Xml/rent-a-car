package com.tim26.AdService.service;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.Car;
import com.tim26.AdService.model.User;
import com.tim26.AdService.repository.AdRepository;
import com.tim26.AdService.repository.UserRepository;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.CarService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    private static final Logger LOGGER=LoggerFactory.getLogger(AdServiceImpl.class);


    @Override
    public boolean save(CreateAdDto ad) {
        Ad advertisment = new Ad();
        Car car = new Car();
        User user = new User();
        user.setId(ad.getUserId());

        if(!userRepository.findById(ad.getUserId()).isPresent()) {
            userRepository.save(ad.getUserId());
        }

        if(ad.getRole().equals("ROLE_USER")) {
            if(user.getAd() != null) {
                if(user.getAd().size() == 3) {
                    return false;
                }
            }
        }

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
            advertisment.setCity(ad.getCity());
            advertisment.setRentDates(ad.getDates());
            user.getAd().add(advertisment);

            advertisment.setUser(user);

            try {
                advertisment = adRepository.save(advertisment);
                return  true;
            } catch (Exception e) {
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
            AdDTO adDTO = new AdDTO(new CarDTO(ad.getCar()),ad.getUser().getId(),ad.getId());
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

    public CarDTO findCarById(Long id){

        CarDTO dto = carService.findById(id);
        return  dto;

    }
}