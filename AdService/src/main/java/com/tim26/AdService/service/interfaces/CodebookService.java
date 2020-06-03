package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.model.BrandModels;
import com.tim26.AdService.model.Codebook;

import java.util.List;

public interface CodebookService {

    Codebook getFirstCodebook();

    Codebook save(Codebook codebook);

    List<BrandModels> getBrands();

    List<String> getModelsFromBrand(String brand);

    List<String> getFuelTypes();

    List<String> getTransmissionTypes();

    List<String> getCarClasses();
}


