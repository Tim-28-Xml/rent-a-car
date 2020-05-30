package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.Codebook;

import java.util.ArrayList;
import java.util.List;

public interface CodebookService {

    Codebook getFirstCodebook();

    Codebook save(Codebook codebook);

    /*List<String> getFuelTypes();

    List<String> getTransmissionTypes();

    List<String> getBrands();

    List<String> getModels();

    List<String> getCarClasses();*/

}
