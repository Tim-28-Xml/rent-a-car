package com.tim26.AdService.model;



import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate date;

    @ManyToOne
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
