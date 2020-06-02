package com.tim26.demo.service;

import com.tim26.demo.model.Ad;
import com.tim26.demo.model.EndUser;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    EndUserService endUserService;

    @Override
    public boolean removeAd(String username, String id){
        EndUser endUser = endUserService.findByUsername(username);
        long adId;

        try {
            adId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }

        for(Ad ad : endUser.getShoppingCart()){
            if(ad.getId() == adId){
                endUser.getShoppingCart().remove(ad);
                endUserService.save(endUser);
            }
        }

        return true;
    };

    @Override
    public void addAd(String username, Ad ad){
        EndUser endUser = endUserService.findByUsername(username);
        endUser.getShoppingCart().add(ad);
        endUserService.save(endUser);
    };
}
