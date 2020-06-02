package com.tim26.AuthenticationService.helpers;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimeProvider {
    public Date now() {
        return new Date();
    }
}
