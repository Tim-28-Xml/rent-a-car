package com.tim26.AdService.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ad", namespace = "http://localhost:8084/adservice-schema")
@XmlRootElement(name = "adClass")
public class Ad {

    @Id
    @XmlElement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlElement
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(joinColumns=@JoinColumn(referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(referencedColumnName = "id"))
    @XmlElement
    private List<DateRange> rentDates = new ArrayList<DateRange>();

    @ManyToOne
    @XmlElement
    private PriceList priceList;

    @OneToMany(mappedBy = "ad")
    @XmlElement
    private List<Report> reports;

    @ManyToOne
    @XmlElement
    private User user;



    @ManyToMany
    @XmlElement
    @JoinTable(name = "ad_rent_requests",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"))
    private List<RentRequest> rentRequests = new ArrayList<>();


    @OneToMany
    @XmlElement
    private List<Review> reviews = new ArrayList<>();

    @Column
    @XmlElement
    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    @XmlElement
    private List<CartAdDates> cartAdDates = new ArrayList<>();

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

    public List<RentRequest> getRentRequests() {
        return rentRequests;
    }

    public void setRentRequests(List<RentRequest> rentRequests) {
        this.rentRequests = rentRequests;
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

    public List<CartAdDates> getCartAdDates() {
        return cartAdDates;
    }

    public void setCartAdDates(List<CartAdDates> cartAdDates) {
        this.cartAdDates = cartAdDates;
    }
}
