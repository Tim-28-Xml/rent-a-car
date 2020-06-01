package com.tim26.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "brands")
public class BrandModels {

    @Id
    private String brand;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> models = new ArrayList<>();

    public BrandModels() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }
}
