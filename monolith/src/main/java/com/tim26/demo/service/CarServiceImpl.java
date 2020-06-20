package com.tim26.demo.service;

import com.tim26.demo.model.Car;
import com.tim26.demo.repository.CarRepository;
import com.tim26.demo.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllByMileage() {

        return carRepository.findAllByOrderByKmDesc();

    }

    @Override
    public List<Car> getAllByRating() {
        return null;
    }

    @Override
    public List<Car> getAllByComments() {
        return null;
    }
}
