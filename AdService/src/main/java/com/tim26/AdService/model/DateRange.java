package com.tim26.AdService.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DateRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(joinColumns=@JoinColumn(referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(referencedColumnName = "id"))
    private List<Date> dates = new ArrayList<>();

    public DateRange() {
    }

    public DateRange(LocalDate startDate, LocalDate endDate,List<Date> dates) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.setDates(dates);
    }

    public DateRange(LocalDate startDate, LocalDate endDate){

        this.startDate = startDate;
        this.endDate = endDate;

        LocalDate start = startDate;
        LocalDate end = endDate;
        List<Date> totalDates = new ArrayList<>();

        while (!start.isAfter(end)) {

            totalDates.add(new Date(start));
            start = start.plusDays(1);
        }


        this.dates = totalDates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}