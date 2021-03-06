package com.tim26.demo.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tim26.demo.model.Ad;
import com.tim26.demo.model.PriceList;
import com.tim26.demo.model.User;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class CreatePricelistDto {
    private String name;
    private double dailyPrice;
    private double cdwPrice;
    private double pricePerExtraKm;
    private String username;


    public CreatePricelistDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public double getCdwPrice() {
        return cdwPrice;
    }

    public void setCdwPrice(double cdwPrice) {
        this.cdwPrice = cdwPrice;
    }

    public double getPricePerExtraKm() {
        return pricePerExtraKm;
    }

    public void setPricePerExtraKm(double pricePerExtraKm) {
        this.pricePerExtraKm = pricePerExtraKm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
