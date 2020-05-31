package com.tim26.demo.service;

import com.tim26.demo.model.BrandModels;
import com.tim26.demo.model.Codebook;
import com.tim26.demo.repository.CodebookRepository;
import com.tim26.demo.service.interfaces.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodebookServiceImpl implements CodebookService {

    @Autowired
    CodebookRepository codebookRepository;

    @Override
    public Codebook getFirstCodebook() {
        return codebookRepository.getFirstCodebook();
    }

    @Override
    public Codebook save(Codebook codebook) {
        return codebookRepository.save(codebook);
    }

    @Override
    public List<BrandModels> getBrands() {
       Codebook codebook = getFirstCodebook();
       return codebook.getBrandModels();
    }

    @Override
    public List<String> getModelsFromBrand(String brand) {
        BrandModels brandmodels = new BrandModels();
        for(BrandModels b : getBrands()) {
            if(b.getBrand().equals(brand)) {
                return b.getModels();
            }
        }
        return null;
    }

    @Override
    public List<String> getFuelTypes() {
        Codebook codebook = getFirstCodebook();
        return codebook.getFuelTypes();
    }

    @Override
    public List<String> getTransmissionTypes() {
        Codebook codebook = getFirstCodebook();
        return codebook.getTransmissionTypes();
    }

    @Override
    public List<String> getCarClasses() {
        Codebook codebook = getFirstCodebook();
        return codebook.getCarClasses();
    }


}
