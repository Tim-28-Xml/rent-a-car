package com.tim26.AdService.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PriceList", namespace = "http://localhost:8084/adservice-schema")
@XmlRootElement(name = "pricelistClass")
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private long id;

    @Column
    @XmlElement
    private double dailyPrice;

    @Column
    @XmlElement
    private double cdwPrice;

    @Column
    @XmlElement
    private double pricePerExtraKm;

    @ManyToOne
    @XmlElement
    private User user;

    @OneToMany(mappedBy = "priceList")
    @XmlElement
    private List<Ad> ads = new ArrayList<>();

    @Column
    @XmlElement
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
