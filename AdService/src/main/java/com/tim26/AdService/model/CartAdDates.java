package com.tim26.AdService.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CartAdDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @ManyToOne(cascade = CascadeType.ALL)
    private  DateRange dateRange;

    public CartAdDates() {
    }

    public CartAdDates(String username, LocalDate startDate, LocalDate endDate) {
        this.username = username;
        this.dateRange = new DateRange();
        this.dateRange.setStartDate(startDate);
        this.dateRange.setEndDate(endDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }
}
