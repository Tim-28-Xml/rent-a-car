package com.tim26.ReportService.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured");
        return "Hello svet from report service";
    }
}
