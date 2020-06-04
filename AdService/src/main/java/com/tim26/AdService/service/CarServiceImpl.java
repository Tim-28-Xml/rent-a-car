package com.tim26.AdService.service;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.model.Car;
import com.tim26.AdService.repository.CarRepository;
import com.tim26.AdService.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    @Override
    public CarDTO findById(Long id) {
        Car car = carRepository.findById(id).get();
        CarDTO dto = new CarDTO(car);
        return dto;
    }
}
