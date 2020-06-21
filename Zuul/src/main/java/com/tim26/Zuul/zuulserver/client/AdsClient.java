package com.tim26.Zuul.zuulserver.client;

import com.tim26.Zuul.zuulserver.dto.AdDTO;
import com.tim26.Zuul.zuulserver.dto.AdDateRange;
import com.tim26.Zuul.zuulserver.dto.RentAdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "adservice")
public interface AdsClient {

    @PostMapping("/api/ads/byIds")
    List<AdDTO> getAdsByIds(@RequestBody List<Long> ids, @RequestHeader("Authorization") String token);

    @DeleteMapping("shoppingcart")
    Boolean deleteMultipleFromShoppingCart(@RequestParam List<Long> id, @RequestHeader("Authorization") String token);

    @PostMapping("api/ads/reserveDates")
    Boolean reserveDateForAd(@RequestBody List<RentAdDTO> rentAdDTOS, @RequestHeader("Authorization") String token);
}
