package com.tim26.Zuul.zuulserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authenticationservice")
@Component
public interface AuthClient {

    @GetMapping("/api/auth/verify")
    boolean verify();

}
