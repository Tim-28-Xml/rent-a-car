package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.model.Ad;

import java.security.Principal;
import java.util.List;

public interface ShoppingCartService {
    boolean removeAd(String username, Long adId);
    void addAd(String username, Long adId);
    List<AdDTO> getCartData(Principal p);
}
