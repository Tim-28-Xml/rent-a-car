package com.tim26.demo.repository;

import com.tim26.demo.model.PriceList;
import com.tim26.demo.service.interfaces.PricelistService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricelistRepository extends JpaRepository<PriceList, Long> {
    PriceList save(PriceList priceList);
    PriceList findByName(String name);
}
