package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.Ad;

public interface ShoppingCartService {
    boolean removeAd(String username, String id);
    void addAd(String username, Ad ad);
}
