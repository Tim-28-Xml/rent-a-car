package com.tim26.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade=CascadeType.ALL)  //proveri za cascade delete
    @JoinTable(joinColumns=@JoinColumn(referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(referencedColumnName = "id"))
    private List<DateRange> rentDates;

    @OneToMany(mappedBy = "ad")
    private List<PriceList> priceLists;

}
