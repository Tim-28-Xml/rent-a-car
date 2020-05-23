package com.tim26.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EndUser extends User {

    @Column
    private String firstname;

    @Column
    private String lastname;

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(joinColumns=@JoinColumn(referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(referencedColumnName = "id", name = "ad_id"))
    private List<Ad> shoppingCart = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "endUser")
    private List<RentRequest> rentRequests;

    public EndUser() {
        super();
    }

    public EndUser(String email, String password, String username, String firstname, String lastname) {
        super(email, password, username);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Ad> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Ad> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
