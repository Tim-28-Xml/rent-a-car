package com.tim26.AdService.service;


import com.tim26.AdService.model.BrandModels;
import com.tim26.AdService.repository.BrandModelsRepository;
import com.tim26.AdService.service.interfaces.BrandModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandModelsServiceImpl implements BrandModelsService {

    @Autowired
    BrandModelsRepository brandModelsRepository;

    @Override
    public BrandModels findByBrand(String brand) {
        return brandModelsRepository.findByBrand(brand);
    }

    @Override
    public BrandModels save(BrandModels brandModels) {
        return brandModelsRepository.save(brandModels);
    }
}
