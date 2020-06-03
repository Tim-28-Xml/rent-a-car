package com.tim26.AdService.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Codebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<BrandModels> brandModels = new ArrayList<>();

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

    public List<BrandModels> getBrandModels() {
        return brandModels;
    }

    public void setBrandModels(List<BrandModels> brandModels) {
        this.brandModels = brandModels;
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
