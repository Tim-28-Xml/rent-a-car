package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.BrandModels;
import com.tim26.demo.model.Codebook;

import java.util.ArrayList;
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
