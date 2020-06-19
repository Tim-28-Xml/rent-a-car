package com.tim26.AdService.service;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.model.*;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.ShoppingCartService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    UserService userService;

    @Autowired
    AdService adService;

    @Override
    public boolean removeAd(String username, Long adId) {
        User user = userService.findByUsername(username);

        for(Ad ad : user.getShoppingCart()){
            if(ad.getId() == adId){
                user.getShoppingCart().remove(ad);
                userService.save(user);

                List<CartAdDates> temp  = new ArrayList<>(ad.getCartAdDates());

                for(CartAdDates cartAdDate: temp) {
                    if (cartAdDate.getUsername().equals(username))
                        ad.getCartAdDates().remove(cartAdDate);
                        adService.save(ad);
                        break;
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAd(String username, Long adId, LocalDate startDate, LocalDate endDate) {
        User user = userService.findByUsername(username);

        if(user == null)
            user = new User(username);

        Ad ad = adService.findAdById(adId);

        if(ad == null)
            return false;
        if(!validateDates(startDate, endDate, ad))
            return false;

        ad.getCartAdDates().add(new CartAdDates(username, startDate, endDate));

        user.getShoppingCart().add(ad);
        userService.save(user);

        return true;
    }

    @Override
    public List<AdDTO> getCartData(Principal p) {
        User user = userService.findByUsername(p.getName());
        if (user == null){
            return null;
        }
        List<AdDTO> cartAds = new ArrayList<>();

        for (Ad ad: user.getShoppingCart()) {
            AdDTO adDTO = new AdDTO(ad);
            adDTO.setId(ad.getId());
            cartAds.add(adDTO);
        }
        return cartAds;
    }

    private boolean validateDates(LocalDate startDate, LocalDate endDate, Ad ad){
        //check rent dates - start end range and already reserved dates in that range
        for (DateRange rng : ad.getRentDates()){
            if(!startDate.isBefore(rng.getStartDate()) && !endDate.isAfter(rng.getEndDate())){
                for(Date date : rng.getDates()){
                    if(!date.getDate().isBefore(startDate) && !date.getDate().isAfter(endDate))
                        return false;
                }
            }
        }

        //check basket for given ad and start end date range
        for(CartAdDates c : ad.getCartAdDates()){
            if(!startDate.isBefore(c.getDateRange().getStartDate()) && !endDate.isAfter(c.getDateRange().getEndDate()))
                return false;
        }

        return true;
    }
}
