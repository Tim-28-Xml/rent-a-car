package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.model.BrandModels;

public interface BrandModelsService {
    BrandModels findByBrand(String brand);
    BrandModels save(BrandModels brandModels);
}
