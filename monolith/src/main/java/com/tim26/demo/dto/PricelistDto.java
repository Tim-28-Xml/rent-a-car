package com.tim26.demo.dto;

public class PricelistDto {
    private String name;
    private double dailyPrice;
    private double cdwPrice;
    private double pricePerExtraKm;
    private Long id;

    public PricelistDto() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
