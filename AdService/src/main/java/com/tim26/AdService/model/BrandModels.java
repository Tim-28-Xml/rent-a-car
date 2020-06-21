package com.tim26.AdService.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "brands")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrandModels", namespace = "http://localhost:8084/adservice-schema")
@XmlRootElement(name = "brandModelsClass")
public class BrandModels {

    @Id
    @XmlElement
    private String brand;

    @ElementCollection(fetch = FetchType.EAGER)
    @XmlElement
    private List<String> models = new ArrayList<>();

    public BrandModels() {
    }

    public BrandModels(String brand, String model) {
        this.brand = brand;
        this.models.add(model);
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