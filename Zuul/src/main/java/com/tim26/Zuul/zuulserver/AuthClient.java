package com.tim26.Zuul.zuulserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "authenticationservice")
@Component
public interface AuthClient {
    

}
