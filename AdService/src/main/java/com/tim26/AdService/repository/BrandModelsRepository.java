package com.tim26.AdService.repository;

import com.tim26.AdService.model.BrandModels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandModelsRepository extends JpaRepository<BrandModels, String> {
    BrandModels findByBrand(String brand);
}
