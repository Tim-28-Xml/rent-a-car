package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CarService {

    CarDTO findById(Long id);
    List<Car> findAll();
}
