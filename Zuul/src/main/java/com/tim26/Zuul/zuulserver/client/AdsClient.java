package com.tim26.Zuul.zuulserver.client;

import com.tim26.Zuul.zuulserver.dto.AdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "adservice")
public interface AdsClient {

    @PostMapping("/api/ads/byIds")
    List<AdDTO> getAdsByIds(@RequestBody List<Long> ids, @RequestHeader("Authorization") String token);
}
