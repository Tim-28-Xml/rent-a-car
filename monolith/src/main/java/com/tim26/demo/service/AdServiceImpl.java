package com.tim26.demo.service;

import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.RentAdDTO;
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
import java.time.LocalDate;
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
                advertisment.setCity(ad.getCity());
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

    @Override
    public List<AdDTO> findMyAds(String username) {
        List<AdDTO> adDTOS = new ArrayList<>();

        User user = userService.findByUsername(username);
        List<Ad> ads = user.getAd();

        for (Ad a : ads) {
            adDTOS.add(new AdDTO(a));
        }

        return adDTOS;
    }

    @Override
    public boolean rentByCreator(RentAdDTO rentAdDTO, Principal p) {
        Optional<Ad> ad = adRepository.findById(rentAdDTO.getId());
        if (!ad.isPresent()) {
            return false;
        }

        LocalDate startDate = rentAdDTO.getStartDate();
        LocalDate endDate = rentAdDTO.getEndDate();

        List<RentRequest> goodRequests = new ArrayList<>();

        for (RentRequest rentRequest : ad.get().getRentRequests()) {
            if (rentRequest.getStartDate().isAfter(startDate) && rentRequest.getStartDate().isBefore(endDate)) {
                break;
            } else if (rentRequest.getEndDate().isAfter(startDate) && rentRequest.getEndDate().isBefore(endDate)) {
                break;
            } else {
                goodRequests.add(rentRequest);
            }
        }

        ad.get().getRentRequests().clear();
        ad.get().setRentRequests(goodRequests);

        DateRange dateRange = new DateRange(startDate, endDate);
        ad.get().getRentDates().add(dateRange);
        adRepository.save(ad.get());

        return true;

    }
}
