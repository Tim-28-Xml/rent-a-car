package com.tim26.AdService.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {


    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured from ad service");
        return "Hello svet from ad service";
    }



}
