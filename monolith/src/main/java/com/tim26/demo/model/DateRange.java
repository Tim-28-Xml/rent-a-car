package com.tim26.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DateRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate startDateA;

    @Column
    private LocalDate endDateA;

    public DateRange() {
    }

    public DateRange(LocalDate startDateA, LocalDate endDateA) {
        this.startDateA = startDateA;
        this.endDateA = endDateA;
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
}
