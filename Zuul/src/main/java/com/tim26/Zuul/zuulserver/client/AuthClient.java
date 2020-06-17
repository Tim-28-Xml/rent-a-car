package com.tim26.Zuul.zuulserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "authenticationservice")
public interface AuthClient {

    @GetMapping("/api/auth/verify/{token}")
    boolean verify(@PathVariable String token);
}
