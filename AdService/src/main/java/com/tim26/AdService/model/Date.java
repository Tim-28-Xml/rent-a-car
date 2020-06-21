package com.tim26.AdService.model;



import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;


@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Date", namespace = "http://localhost:8084/adservice-schema")
@XmlRootElement(name = "dateClass")
public class Date {

    @Id
    @XmlElement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @XmlElement
    private LocalDate date;

    @ManyToOne
    @XmlElement
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
