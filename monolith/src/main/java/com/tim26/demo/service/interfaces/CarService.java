package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.Car;

import java.util.List;

public interface CarService {

    public List<Car> getAllByMileage();
    public List<Car> getAllByRating();
    public List<Car> getAllByComments();

}
