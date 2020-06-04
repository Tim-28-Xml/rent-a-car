package com.tim26.AdService.service;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.model.Ad;
import com.tim26.AdService.repository.AdRepository;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CarService carService;


    @Override
    public boolean save(AdDTO ad, Principal p) {
       /* EndUser endUser = endUserService.findByUsername(p.getName());
        if(endUser != null) {

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

                DateRange dateRange = new DateRange();
                dateRange.setStartDate(ad.getStartDate());
                dateRange.setEndDate(ad.getEndDate());
                List<DateRange> dateRanges = new ArrayList<DateRange>();
                dateRanges.add(dateRange);
                advertisment.setRentDates(dateRanges);

                endUser.getAd().add(advertisment);
                advertisment.setUser(endUser);

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
        }*/
       return  false;
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