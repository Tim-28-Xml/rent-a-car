package com.tim26.demo.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DateRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate startDateA;

    @Column
    private LocalDate endDateA;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(joinColumns=@JoinColumn(referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(referencedColumnName = "id"))
    private List<Date> dates = new ArrayList<>();

    public DateRange() {
    }

    public DateRange(LocalDate startDate, LocalDate endDate,List<Date> dates) {
        this.startDateA = startDate;
        this.endDateA = endDate;
        this.setDates(dates);
    }

    public DateRange(LocalDate startDate, LocalDate endDate){

        this.startDateA = startDate;
        this.endDateA = endDate;

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

    public LocalDate getStartDateA() {
        return startDateA;
    }

    public void setStartDateA(LocalDate startDateA) {
        this.startDateA = startDateA;
    }

    public LocalDate getEndDateA() {
        return endDateA;
    }

    public void setEndDateA(LocalDate endDateA) {
        this.endDateA = endDateA;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
