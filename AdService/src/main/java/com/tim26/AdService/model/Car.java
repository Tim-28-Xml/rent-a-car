package com.tim26.AdService.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Car", namespace = "http://localhost:8084/adservice-schema")
@XmlRootElement(name = "carClass")
public class Car {

    @Id
    @XmlElement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @XmlElement
    private String brand;

    @Column(nullable = false)
    @XmlElement
    private String model;

    @Column(nullable = false)
    @XmlElement
    private String fuel;

    @Column(nullable = false)
    @XmlElement
    private String transmission;

    @Column(nullable = false)
    @XmlElement
    private String carClass;

    @Column(nullable = false)
    @XmlElement
    private double km;

    @Column(nullable = false)
    @XmlElement
    private double kmLimit;

    @Column(nullable = false)
    @XmlElement
    private boolean cdw;

    @Column(nullable = false)
    @XmlElement
    private int childSeats;

    @ElementCollection
    @XmlElement
    private List<byte[]> files = new ArrayList<byte[]>();

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getKmLimit() {
        return kmLimit;
    }

    public void setKmLimit(double kmLimit) {
        this.kmLimit = kmLimit;
    }

    public boolean isCdw() {
        return cdw;
    }

    public void setCdw(boolean cdw) {
        this.cdw = cdw;
    }

    public int getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(int childSeats) {
        this.childSeats = childSeats;
    }

    public List<byte[]> getFiles() {
        return files;
    }

    public void setFiles(List<byte[]> files) {
        this.files = files;
    }
}

