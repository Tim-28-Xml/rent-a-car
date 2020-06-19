package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.model.Ad;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface ShoppingCartService {
    boolean removeAd(String username, Long adId);
    boolean addAd(String username, Long adId, LocalDate startDate, LocalDate endDate);
    List<AdDTO> getCartData(Principal p);
}
