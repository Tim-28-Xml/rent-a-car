package com.tim26.AdService.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "Codebook", namespace = "http://localhost:8084/adservice-schema")
//@XmlRootElement(name = "codebookClass")
public class Codebook {

    @Id
    //@XmlElement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    //@XmlElement
    private List<BrandModels> brandModels = new ArrayList<>();

    @ElementCollection
    //@XmlElement
    private List<String> fuelTypes;

    @ElementCollection
    //@XmlElement
    private List<String> transmissionTypes;

    @ElementCollection
    //@XmlElement
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
