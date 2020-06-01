package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.BrandModels;

public interface BrandModelsService {
    BrandModels findByBrand(String brand);
    BrandModels save(BrandModels brandModels);
}
