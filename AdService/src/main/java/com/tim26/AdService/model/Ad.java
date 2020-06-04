package com.tim26.AdService.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(joinColumns=@JoinColumn(referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(referencedColumnName = "id"))
    private List<DateRange> rentDates;

    @ManyToOne
    private PriceList priceList;

    @OneToMany(mappedBy = "ad")
    private List<Report> reports;

    @ManyToOne
    private User user;

    @ManyToOne
    private RentRequest rentRequest;


    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @Column(nullable = false)
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

    public RentRequest getRentRequest() {
        return rentRequest;
    }

    public void setRentRequest(RentRequest rentRequest) {
        this.rentRequest = rentRequest;
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
}
