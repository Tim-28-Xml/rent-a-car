package com.tim26.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(joinColumns=@JoinColumn(referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(referencedColumnName = "id"))
    private List<DateRange> rentDates;

    @ManyToOne
    private PriceList priceList;

    @OneToMany
    private List<Report> reports = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "ad_rent_requests",
            joinColumns = @JoinColumn(name = "ad_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"))
    private List<RentRequest> rentRequests = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @Column
    private String city;

    public Ad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DateRange> getRentDates() {
        return rentDates;
    }

    public void setRentDates(List<DateRange> rentDates) {
        this.rentDates = rentDates;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<RentRequest> getRentRequests() {
        return rentRequests;
    }

    public void setRentRequests(List<RentRequest> rentRequests) {
        this.rentRequests = rentRequests;
    }
}
