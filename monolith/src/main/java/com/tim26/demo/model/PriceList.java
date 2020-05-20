package com.tim26.demo.model;

import javax.persistence.*;

@Entity
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double dailyPrice;

    @Column
    private double cdwPrice;

    @Column
    private double pricePerExtraKm;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ad ad;

    public PriceList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
