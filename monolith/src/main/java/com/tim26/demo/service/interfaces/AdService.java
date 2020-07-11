package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.CreateAdDto;
import com.tim26.demo.dto.RentAdDTO;

import java.security.Principal;
import java.util.List;

public interface AdService {
    boolean save(CreateAdDto ad);
    List<AdDTO> findAll();
    AdDTO findById(long id);
    List<AdDTO> findMyAds(String username);
    boolean rentByCreator(RentAdDTO rentAdDTO, Principal p);
    List<AdDTO> findHighestMileage(Principal p);
    List<AdDTO> findHighestRating(Principal p);
    List<AdDTO> findMostReviews(Principal p);
    boolean isMyAd(Principal p,Long id );


}
