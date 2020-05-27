package com.tim26.Zuul.zuulserver;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "userervice")
public interface AuthClient {


}
