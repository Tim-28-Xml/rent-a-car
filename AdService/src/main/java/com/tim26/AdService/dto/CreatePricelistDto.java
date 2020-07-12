package com.tim26.AdService.dto;

import com.tim26.AdService.model.PriceList;

public class CreatePricelistDto {
    private String name;
    private double dailyPrice;
    private double cdwPrice;
    private double pricePerExtraKm;
    private String username;

    public CreatePricelistDto() {}

    public CreatePricelistDto(String name,double dailyPrice, double cdwPrice, double pricePerExtraKm){
        this.name = name;
        this.dailyPrice = dailyPrice;
        this.cdwPrice = cdwPrice;
        this.pricePerExtraKm = pricePerExtraKm;
    }

    public CreatePricelistDto(PriceList priceList){
        this(priceList.getName(),priceList.getDailyPrice(),priceList.getCdwPrice(),priceList.getPricePerExtraKm());
    }

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
