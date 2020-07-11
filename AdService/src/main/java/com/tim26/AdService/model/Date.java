package com.tim26.AdService.model;



//import com.tim26.AdService.adapter.LocalDateAdapter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "Date", namespace = "http://localhost:8084/adservice-schema")
//@XmlRootElement(name = "dateClass")
public class Date {

    @Id
    //@XmlElement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    //@XmlElement
    //@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate date;

    @ManyToOne
    //@XmlElement
    private DateRange dateRange;

    public Date() {
    }

    public Date(LocalDate date){
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
