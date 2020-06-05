package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.model.Ad;

public interface ShoppingCartService {
    boolean removeAd(Long userId, Long adId);
    void addAd(Long userId, Long adId);
}
