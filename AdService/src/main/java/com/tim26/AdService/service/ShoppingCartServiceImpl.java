package com.tim26.AdService.service;

import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.User;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.ShoppingCartService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    UserService userService;

    @Autowired
    AdService adService;

    @Override
    public boolean removeAd(Long userId, Long adId) {
        User user = userService.findById(userId);

        for(Ad ad : user.getShoppingCart()){
            if(ad.getId() == adId){
                user.getShoppingCart().remove(ad);
                userService.save(user);
            }
        }

        return true;
    }

    @Override
    public void addAd(Long userId, Long adId) {
        User user = userService.findById(userId);
        Ad ad = adService.findAdById(adId);
        user.getShoppingCart().add(ad);
        userService.save(user);
    }
}
