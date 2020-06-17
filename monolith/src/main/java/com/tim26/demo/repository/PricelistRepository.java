package com.tim26.demo.repository;

import com.tim26.demo.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricelistRepository extends JpaRepository<PriceList, Long> {
    PriceList save(PriceList priceList);
    boolean findByName(String name);
}
