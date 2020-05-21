package com.tim26.demo.model;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "priceList")
    private List<Ad> ads;

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

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
