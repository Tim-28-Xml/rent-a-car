package com.tim26.AdService.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Ad> ad;

    @OneToMany(mappedBy = "user")
    private List<PriceList> priceLists;

    @OneToMany
    private List<Ad> shoppingCart = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
