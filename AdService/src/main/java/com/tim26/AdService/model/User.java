package com.tim26.AdService.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @Column
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Ad> ad = new ArrayList<Ad>();

    @OneToMany(mappedBy = "user")
    private List<PriceList> priceLists = new ArrayList<PriceList>();

    @OneToMany
    private List<Ad> shoppingCart = new ArrayList<>();

    public User() {
    }

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
}
