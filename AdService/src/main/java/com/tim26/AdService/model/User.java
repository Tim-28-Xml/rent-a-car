package com.tim26.AdService.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "User", namespace = "http://localhost:8084/adservice-schema")
//@XmlRootElement(name = "userClass")
public class User {

    @Id
    @Column
    //@XmlElement
    private String username;

    @OneToMany(mappedBy = "user")
    //@XmlElement
    private List<Ad> ad = new ArrayList<Ad>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    //@XmlElement
    private List<PriceList> priceLists = new ArrayList<PriceList>();

    @OneToMany()
    //@XmlElement
    private List<Ad> shoppingCart = new ArrayList<>();

    @OneToMany()
    private List<Report> reports = new ArrayList<>();

    @Column
    private int unpaid = 0;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public List<Ad> getAd() {
        return ad;
    }


    public void setAd(List<Ad> ad) {
        this.ad = ad;
    }

    public List<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public List<Ad> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Ad> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public int getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(int unpaid) {
        this.unpaid = unpaid;
    }

    public void incUnpaid(){
        this.unpaid++;
    }

    public void decUnpaid(){
        this.unpaid--;
    }
}
