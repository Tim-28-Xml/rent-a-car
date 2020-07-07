package com.tim26.AdService.controller;

import com.tim26.AdService.dto.*;
import com.tim26.AdService.model.Ad;
import com.tim26.AdService.service.interfaces.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ads", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {
    private static final Logger LOGGER= LoggerFactory.getLogger(AdController.class);
    @Autowired
    private AdService adService;

    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured from ad service");
        return "Hello svet from ad service";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AdDTO>> getAllAds(){

        List<AdDTO> ads = adService.findAll();
        LOGGER.info("Gettings all ads {}", ads.size());
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping(value = "/all/pageable", params = {"page"})
    public ResponseEntity<Page<AdDTO>> getAllAdsPageable(@RequestParam("page") int page){

        Page<AdDTO> ads = adService.findAllPageable(page);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping(value = "/one/{id}")
    public ResponseEntity<AdDTO> getAd(@PathVariable String id){
        AdDTO ad = adService.findById(Long.parseLong(id));
        if(ad != null) {
            LOGGER.info("Response is 200 OK, GET AD with id: {} from owner: ", ad.getId(), ad.getUsername());
            return new ResponseEntity<>(ad,HttpStatus.OK);
        } else {
            LOGGER.error("Response is 400 BAD REQUEST, Failed GETTING AD with id: {} from owner: ", ad.getId(), ad.getUsername());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable String id){

        CarDTO dto = adService.findCarById(Long.parseLong(id));
        String cdw = "";
        if(dto != null) {
            if(dto.isCdw()) {
                cdw = "Yes";
            } else {
                cdw = "No";
            }
            LOGGER.info("Response is 200 OK, GET CAR with provided id: {} - brand: {}, model: {}, fuel type: {}, transmission type: {}, car class: {}, with collision damage waiver: {}, number of child seats: {}" , dto.getId(), dto.getBrand(), dto.getModel(), dto.getFuel(), dto.getTransmission(), dto.getCarClass(), cdw, dto.getChildSeats());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            LOGGER.error("Response is 400 BAD REQUEST, Failed GETTING CAR with id: {}", dto.getId());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasAuthority('CREATE_AD')")
    @PostMapping(value = "/save")
    public ResponseEntity<CreateAdDto> save(@RequestBody CreateAdDto createAdDto, Principal p) throws SQLException {
        if(adService.save(createAdDto, p)) {
            String cdw = "";
            if(createAdDto.isCollision()) {
                cdw = "Yes";
            } else {
                cdw = "No";
            }
            LOGGER.info("Response is 200 OK, User {} executed the action CREATE NEW ADVERTISMENT \n with car brand: {}, model: {}, \n fuel type: {}, transmission type: {}, car class: {}, \n number of child seats: {}, with collision damage waiver: {}, pricelist name: {}\n", p.getName(), createAdDto.getBrand(), createAdDto.getModel(), createAdDto.getFuel(), createAdDto.getTransmission(), createAdDto.getCarClass(), createAdDto.getChildSeats(), cdw, createAdDto.getPricelist());
            return new ResponseEntity<>(createAdDto, HttpStatus.OK);
        }

        String cdw = "";
        if(createAdDto.isCollision()) {
            cdw = "Yes";
        } else {
            cdw = "No";
        }
        LOGGER.error("Response is 400 BAD REQUEST, Failed to CREATE NEW AD \n with car brand: {}, model: {}, \n fuel type: {}, transmission type: {}, car class: {}, \n number of child seats: {}, with collision damage waiver: {}, pricelist name: {} and owner: {}\n", createAdDto.getBrand(), createAdDto.getModel(), createAdDto.getFuel(), createAdDto.getTransmission(), createAdDto.getCarClass(), createAdDto.getChildSeats(), cdw, createAdDto.getPricelist(), p.getName());
        return new ResponseEntity<>(createAdDto, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('VIEW_MY_ADS')")
    @GetMapping(value = "/my-ads")
    public ResponseEntity<List<AdDTO>> getMyAds(Principal p){
        List<AdDTO> ads = adService.findMyAds(p.getName());
        if(ads != null) {
            LOGGER.info("Response is 200 OK, GET ALL {} ads from user", ads.size(), p.getName());
            return new ResponseEntity<>(ads, HttpStatus.OK);
        }

        LOGGER.warn("User {} doesn't have any ads", p.getName());
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RENT_BY_CREATOR')")
    @PostMapping(value = "rent-creator")
    public ResponseEntity<String> rentAdByCreator(@RequestBody RentAdDTO rentAdDTO, Principal p){
        if(adService.rentByCreator(rentAdDTO, p)){
            LOGGER.info("Response is 200 OK, PHYISICAL RENT by user {} for ad with id: {} from {} till {}", p.getName(), rentAdDTO.getId(), rentAdDTO.getStartDate(), rentAdDTO.getEndDate());
            return new ResponseEntity<>("Car is successfully rented!",HttpStatus.OK);
        } else {
            LOGGER.error("Response is 400 BAD REQUEST, Failed to PHYSICALLY RENT by user {} for ad with id: {} from {} till {}", p.getName(), rentAdDTO.getId(), rentAdDTO.getStartDate(), rentAdDTO.getEndDate());
            return new ResponseEntity<>("Car cannot be rented!",HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/byIds")
    public List<AdDTO> getAdsByIds(@RequestBody List<Long> ids){
        List<AdDTO> adDTOS = new ArrayList<>();
        List<Ad> ads = adService.findByIds(ids);

        if(ads == null)
            return null;

        for (Ad ad : ads){
            adDTOS.add(new AdDTO(ad));
        }
        return adDTOS;
    }


    @PostMapping(value = "/filter")
    public ResponseEntity<Page<AdDTO>> filterAds(@RequestBody FilterDTO filterDTO){

        Page<AdDTO> ads = adService.filterAds(filterDTO);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping(value = "/filter/parameters")
    public ResponseEntity<FilterParametersDTO> getFilterParameters(){

        FilterParametersDTO parameters = adService.getFilterParamteres();
        return new ResponseEntity<>(parameters, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('RENT_BY_CREATOR','RENT')")
    @PostMapping("/reserveDates")
    public Boolean reserveDateForAds(@RequestBody List<RentAdDTO> rentAdDTOS){
        return adService.setRentDatesForAds(rentAdDTOS);
    }



}
