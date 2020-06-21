package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.RentAdDTO;
import com.tim26.demo.model.Ad;

import java.security.Principal;
import java.util.List;

public interface AdService {
    boolean save(CreateAdDto ad, Principal p);
    List<AdDTO> findAll();
    AdDTO findById(long id);
    List<AdDTO> findMyAds(String username);
    boolean rentByCreator(RentAdDTO rentAdDTO, Principal p);
    List<AdDTO> findHighestMileage(Principal p);
    List<AdDTO> findHighestRating(Principal p);
    List<AdDTO> findMostReviews(Principal p);
    boolean isMyAd(Principal p,Long id );


}
