package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.model.Ad;

public interface ShoppingCartService {
    boolean removeAd(String username, Long adId);
    boolean addAd(String username, Long adId);
}
