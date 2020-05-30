package com.tim26.demo.service;

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

    /*@Override
    public List<String> getFuelTypes() {
        return codebookRepository.findAllByFuelTypes();
    }

    @Override
    public List<String> getTransmissionTypes() {
        return codebookRepository.findAllByTransmissionTypes();
    }

    @Override
    public List<String> getBrands() {
        return codebookRepository.findAllByBrandModels();
    }

    @Override
    public List<String> getModels() {
        return null;
    }

    @Override
    public List<String> getCarClasses() {
        return codebookRepository.findAllByCarClasses();
    }*/
}
