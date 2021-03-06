package com.tim26.AdService.repository;

import com.tim26.AdService.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricelistRepository extends JpaRepository<PriceList, String> {
    PriceList save(PriceList priceList);
    PriceList findByName(String name);
    PriceList findById(Long id);
}
