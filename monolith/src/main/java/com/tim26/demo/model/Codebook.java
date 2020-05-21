package com.tim26.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Codebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> brands;

    @ElementCollection
    private Map<String, String> models;

    @ElementCollection
    private List<String> fuelTypes;

    @ElementCollection
    private List<String> transmissionTypes;

    @ElementCollection
    private List<String> carClasses;

    public Codebook() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public Map<String, String> getModels() {
        return models;
    }

    public void setModels(Map<String, String> models) {
        this.models = models;
    }

    public List<String> getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(List<String> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public List<String> getTransmissionTypes() {
        return transmissionTypes;
    }

    public void setTransmissionTypes(List<String> transmissionTypes) {
        this.transmissionTypes = transmissionTypes;
    }

    public List<String> getCarClasses() {
        return carClasses;
    }

    public void setCarClasses(List<String> carClasses) {
        this.carClasses = carClasses;
    }
}
